package commaciejprogramuje.facebook.conferenceapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by m.szymczyk on 2017-11-09.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView summaryTV;
    public TextView dtStartTv;
    public TextView dtEndTv;

    public MyViewHolder(View itemView) {
        super(itemView);
        summaryTV = itemView.findViewById(R.id.meeting_summary);
        dtStartTv = itemView.findViewById(R.id.meeting_dt_start);
        dtEndTv = itemView.findViewById(R.id.meeting_dt_end);
    }
}
