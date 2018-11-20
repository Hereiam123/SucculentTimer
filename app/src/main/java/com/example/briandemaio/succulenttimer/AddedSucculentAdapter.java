package com.example.briandemaio.succulenttimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AddedSucculentAdapter extends RecyclerView.Adapter<AddedSucculentAdapter.SucculentViewHolder> {

    private final Context mContext;

    private final LayoutInflater mInflater;
    private List<Succulent> mSucculents;

    AddedSucculentAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context); }

    @Override
    public SucculentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.added_view_item, parent, false);
        return new SucculentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SucculentViewHolder holder, int position) {
        if (mSucculents != null) {
            final Succulent current = mSucculents.get(position);
            holder.succulentItemView.setText(current.getName());
            holder.succulentResetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent notifyIntent = new Intent(mContext, AlarmReceiver.class);
                    long triggerTime = System.currentTimeMillis() + 30 * 200;
                    int previousId = current.getTimeId();
                    notifyIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, previousId);
                    notifyIntent.putExtra(AlarmReceiver.NOTIFICATION, current.getName());
                    PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                            (mContext, previousId, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            triggerTime, notifyPendingIntent);
                }
            });
            Glide.with(mContext).load(current.getImageResource()).into(holder.succulentImageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.succulentItemView.setText("No Succulent");
        }
    }

    public Succulent getSucculentAtPosition (int position) {
        return mSucculents.get(position);
    }

    void setSucculents(List<Succulent> succulents){
        mSucculents = succulents;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSucculents != null)
            return mSucculents.size();
        else return 0;
    }

    class SucculentViewHolder extends RecyclerView.ViewHolder {
        private final TextView succulentItemView;
        private final ImageView succulentImageView;
        private final ImageButton succulentResetView;

        private SucculentViewHolder(View itemView) {
            super(itemView);
            succulentItemView = itemView.findViewById(R.id.recycler_textview_succulent_name);
            succulentImageView = itemView.findViewById(R.id.recycler_imageview_succulent_art);
            succulentResetView = itemView.findViewById(R.id.recycler_reset_timer);
        }
    }
}
