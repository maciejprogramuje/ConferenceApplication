package commaciejprogramuje.facebook.conferenceapplication;

/**
 * Created by m.szymczyk on 2017-11-08.
 */

public class OneMeeting {
    private String summary;
    private String startDate;
    private String endDate;

    public OneMeeting(String summary, String startDate, String endDate, String reservationDate) {
        this.summary = summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationDate = reservationDate;
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
