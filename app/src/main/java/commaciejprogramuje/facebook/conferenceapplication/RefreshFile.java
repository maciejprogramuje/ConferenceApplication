package commaciejprogramuje.facebook.conferenceapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by m.szymczyk on 2017-11-03.
 */

public class RefreshFile extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        new ParsePage().execute();
    }
}
