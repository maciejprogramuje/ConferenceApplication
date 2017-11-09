package commaciejprogramuje.facebook.conferenceapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String INPUT_FILE_URL = "https://poczta.pb.pl/home/sala_akwarium@pb.pl/Calendar/";

    private RecyclerView recyclerView;
    private String getFilesDir;
    private ArrayList<OneMeeting> meetings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFilesDir = getFilesDir().getAbsolutePath();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        meetings.add(new OneMeeting("Launching..." , "", "", ""));


        ParsePage firstParsingPage = new ParsePage(new ParsePage.OnTaskCompletedListener() {
            @Override
            public void onTaskCompletedListener(ArrayList<OneMeeting> parsingResultArr) {
                meetings = parsingResultArr;
                recyclerView.setAdapter(new MyAdapter(meetings, recyclerView));
            }
        });
        firstParsingPage.execute(getFilesDir);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParsePage onClickParsingPage = new ParsePage(new ParsePage.OnTaskCompletedListener() {
                    @Override
                    public void onTaskCompletedListener(ArrayList<OneMeeting> parsingResultArr) {
                        meetings = parsingResultArr;
                        // o 9.30 zmiana
                        //recyclerView.getAdapter().notifyDataSetChanged();
                        recyclerView.setAdapter(new MyAdapter(meetings, recyclerView));
                    }
                });
                onClickParsingPage.execute(getFilesDir);
            }
        });

        Intent alarmIntent = new Intent(this, RefreshFile.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 111, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 2, pendingIntent);
    }

    public static void refreshAgenda() {
        ParsePage refreshParsingPage = new ParsePage(new ParsePage.OnTaskCompletedListener() {
            @Override
            public void onTaskCompletedListener(ArrayList<OneMeeting> parsingResultArr) {
                // o 9.30 zmiana
                //recyclerView.getAdapter().notifyDataSetChanged();
                RecyclerView rv =

                recyclerView.setAdapter(new MyAdapter(parsingResultArr, recyclerView));
            }
        });
        refreshParsingPage.execute(getFilesDir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
