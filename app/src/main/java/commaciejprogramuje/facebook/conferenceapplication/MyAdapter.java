package commaciejprogramuje.facebook.conferenceapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by m.szymczyk on 2017-11-09.
 */

class MyAdapter extends RecyclerView.Adapter {
    private RecyclerView recyclerView;
    ArrayList<OneMeeting> meetings;

    public MyAdapter(ArrayList<OneMeeting> meetings, RecyclerView recyclerView) {
        this.meetings = meetings;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_meeting, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OneMeeting oneMeeting = meetings.get(position);
        ((MyViewHolder) holder).summaryTV.setText(oneMeeting.getSummary());
        ((MyViewHolder) holder).dtStartTv.setText(oneMeeting.getStartDate());
        ((MyViewHolder) holder).dtEndTv.setText(oneMeeting.getEndDate());
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }
}
