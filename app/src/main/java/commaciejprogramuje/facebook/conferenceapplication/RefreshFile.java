package commaciejprogramuje.facebook.conferenceapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static commaciejprogramuje.facebook.conferenceapplication.MainActivity.GET_FILES_DIR;
import static commaciejprogramuje.facebook.conferenceapplication.MainActivity.INPUT_FILE_URL;
import static commaciejprogramuje.facebook.conferenceapplication.MainActivity.RESULT_FILE_NAME;

/**
 * Created by m.szymczyk on 2017-11-03.
 */

public class RefreshFile extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        new Parse().execute();
    }

    private static class Parse extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.w("UWAGA", "parsuję...");

            try {
                URL url = new URL(INPUT_FILE_URL);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                int responseCode = httpConn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpConn.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(new File(GET_FILES_DIR, RESULT_FILE_NAME));

                    int bytesRead = -1;
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.close();
                    inputStream.close();

                    Log.w("UWAGA", "File downloaded: " + GET_FILES_DIR);
                } else {
                    Log.w("UWAGA", "No file to download. Server replied HTTP code: " + responseCode);
                }
                httpConn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(RESULT_FILE_NAME));
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(stringBuilder);

            return null;
        }
    }
}
