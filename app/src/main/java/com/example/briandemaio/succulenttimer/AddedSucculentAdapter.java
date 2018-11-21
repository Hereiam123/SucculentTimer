package com.example.briandemaio.succulenttimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

public class AddedSucculentAdapter extends RecyclerView.Adapter<AddedSucculentAdapter.SucculentViewHolder> {

    private final Context mContext;

    private final LayoutInflater mInflater;
    private List<Succulent> mSucculents;
    private static ClickListener clickListener;


    AddedSucculentAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context); }

    @Override
    public SucculentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.added_view_item, parent, false);
        return new SucculentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SucculentViewHolder holder, int position) {
        if (mSucculents != null) {
            final Succulent current = mSucculents.get(position);
            holder.succulentItemView.setText(current.getName());
            if(holder.succulentTimer != null){
                holder.succulentTimer.cancel();
            }

            holder.succulentTimer = new CountDownTimer(current.getExpiryTime()-System.currentTimeMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    holder.succulentTimerView.setText(" " + (int) (millisUntilFinished/1000) % (60) +" seconds left ");
                }

                @Override
                public void onFinish() {
                    holder.succulentTimerView.setText("All Done");
                }
            }.start();
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

    class SucculentViewHolder extends RecyclerView.ViewHolder{
        private final TextView succulentItemView;
        private final ImageView succulentImageView;
        private final ImageButton succulentResetView;
        private final TextView succulentTimerView;
        private CountDownTimer succulentTimer;

        private SucculentViewHolder(View itemView) {
            super(itemView);
            succulentItemView = itemView.findViewById(R.id.recycler_textview_succulent_name);
            succulentImageView = itemView.findViewById(R.id.recycler_imageview_succulent_art);
            succulentResetView = itemView.findViewById(R.id.recycler_reset_timer);
            succulentTimerView = itemView.findViewById(R.id.recycler_textview_succulent_timeleft);
            succulentResetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        AddedSucculentAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
