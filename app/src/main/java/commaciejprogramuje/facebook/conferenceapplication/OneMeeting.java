package commaciejprogramuje.facebook.conferenceapplication;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by m.szymczyk on 2017-11-08.
 */

public class OneMeeting implements Comparable<OneMeeting> {
    private String summary;
    private String startDate;
    private String endDate;

    public OneMeeting(String summary, String startDate, String endDate, String reservationDate) {
        this.summary = summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationDate = reservationDate;
    }

    @Override
    public int compareTo(@NonNull OneMeeting oneMeeting) {
        return startDate.compareTo(oneMeeting.startDate);
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    private String reservationDate;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
