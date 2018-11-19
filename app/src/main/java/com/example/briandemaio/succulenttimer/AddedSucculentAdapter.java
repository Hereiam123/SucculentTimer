package com.example.briandemaio.succulenttimer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AddedSucculentAdapter extends RecyclerView.Adapter<AddedSucculentAdapter.SucculentViewHolder> {

    private final Context mContext;

    private final LayoutInflater mInflater;
    private List<Succulent> mSucculents; // Cached copy of words

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
            Succulent current = mSucculents.get(position);
            holder.succulentItemView.setText(current.getName());
            Glide.with(mContext).load(current.getImageResource()).into(holder.succulentImageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.succulentItemView.setText("No Succulent");
        }
    }

    void setSucculents(List<Succulent> succulents){
        mSucculents = succulents;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSucculents != null)
            return mSucculents.size();
        else return 0;
    }

    class SucculentViewHolder extends RecyclerView.ViewHolder {
        private final TextView succulentItemView;
        private final ImageView succulentImageView;

        private SucculentViewHolder(View itemView) {
            super(itemView);
            succulentItemView = itemView.findViewById(R.id.recycler_textview_succulent_name);
            succulentImageView = itemView.findViewById(R.id.recycler_imageview_succulent_art);
        }
    }
}
