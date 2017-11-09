package commaciejprogramuje.facebook.conferenceapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by m.szymczyk on 2017-11-03.
 */

public class RefreshFile extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Refresh", Toast.LENGTH_LONG).show();

        mainActivity.refreshAgenda();
    }
}
