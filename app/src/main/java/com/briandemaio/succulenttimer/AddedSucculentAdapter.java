package com.briandemaio.succulenttimer;

import android.content.Context;
import android.os.CountDownTimer;
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
    private static ClickListener clickListener;


    AddedSucculentAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

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
                    int days = (int) (millisUntilFinished / (1000*60*60*24));
                    int hours = (int) (millisUntilFinished / (1000*60*60)%24);
                    int minutes = (int) (millisUntilFinished / (1000*60)%60);
                    int seconds = (int) (millisUntilFinished / 1000) % 60;
                    if(days>0) {
                        holder.succulentTimerView.setText(" " + days + "d: "+ hours + "h: "+minutes+"m: "+seconds+"s");
                    }
                    else if(hours > 0){
                        holder.succulentTimerView.setText(" "+ hours+" h:" + minutes + " m:"+seconds+" s");
                    }
                    else{
                        holder.succulentTimerView.setText(" " + minutes + " m:" + seconds + " s");
                    }
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

    Succulent getSucculentAtPosition (int position) {
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
            succulentTimerView = itemView.findViewById(R.id.recycler_textview_succulent_timeleft);
            succulentResetView = itemView.findViewById(R.id.recycler_reset_timer);
            succulentResetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onWaterItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    void setOnItemClickListener(ClickListener clickListener) {
        AddedSucculentAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onWaterItemClick(View v, int position);
    }
}
