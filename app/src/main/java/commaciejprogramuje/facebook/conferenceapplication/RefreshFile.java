package commaciejprogramuje.facebook.conferenceapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by m.szymczyk on 2017-11-03.
 */

public class RefreshFile extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String getFilesDir = context.getFilesDir().getAbsolutePath();
        ParsePage parsePage = new ParsePage(new ParsePage.OnTaskCompletedListener() {
            @Override
            public void onTaskCompletedListener(ArrayList<OneMeeting> arrayList) {
                MainActivity.meetingsArr = arrayList;
                for (OneMeeting o : MainActivity.meetingsArr) {
                    Log.w("UWAGA", o.getSummary() + ", " + o.getStartDate() + ", " + o.getEndDate() + ", " + o.getReservationDate());
                }
            }
        });
        parsePage.execute(getFilesDir);
    }
}
