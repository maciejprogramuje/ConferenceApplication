package commaciejprogramuje.facebook.conferenceapplication;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static commaciejprogramuje.facebook.conferenceapplication.MainActivity.INPUT_FILE_URL;

/**
 * Created by m.szymczyk on 2017-11-08.
 */

public class ParsePage extends AsyncTask<String, Void, ArrayList<OneMeeting>> {
    public OnTaskCompletedListener listener = null;

    public ParsePage(OnTaskCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<OneMeeting> doInBackground(String... strings) {
        Log.w("UWAGA", "parsuję...");

        String fileName = "result.txt";
        String resultFileName;
        resultFileName = strings[0] + "/" + fileName;

        try {
            URL url = new URL(INPUT_FILE_URL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConn.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(new File(strings[0], fileName));

                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();

                Log.w("UWAGA", "File downloaded: " + strings[0]);
            } else {
                Log.w("UWAGA", "No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<OneMeeting> tempArr = new ArrayList<>();
        Calendar actualCalendar = Calendar.getInstance();

        try {
            BufferedReader br = new BufferedReader(new FileReader(resultFileName));
            String line;
            String tempSummary = "";
            String tempDtStart = "";
            String tempDtEnd = "";
            String tempDtStamp = "";
            while ((line = br.readLine()) != null) {
                if (line.contains("SUMMARY")) {
                    tempSummary = line.replace("SUMMARY:", "");
                } else if (line.contains("DTSTART")) {
                    tempDtStart = line.substring(line.indexOf(":") + 1);
                } else if (line.contains("DTEND")) {
                    tempDtEnd = line.substring(line.indexOf(":") + 1);
                } else if (line.contains("DTSTAMP")) {
                    tempDtStamp = line.substring(line.indexOf(":") + 1);
                } else if (line.contains("END:VEVENT")) {
                    Calendar oneMeetingCallendar = Utils.initCallendarByString(tempDtEnd);
                    if (oneMeetingCallendar.after(actualCalendar)) {
                        tempArr.add(new OneMeeting(tempSummary, tempDtStart, tempDtEnd, tempDtStamp));
                        Collections.sort(tempArr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempArr;
    }

    @Override
    protected void onPostExecute(ArrayList<OneMeeting> oneMeetings) {
        listener.onTaskCompletedListener(oneMeetings);
    }

    public interface OnTaskCompletedListener {
        void onTaskCompletedListener(ArrayList<OneMeeting> arrayList);
    }
}