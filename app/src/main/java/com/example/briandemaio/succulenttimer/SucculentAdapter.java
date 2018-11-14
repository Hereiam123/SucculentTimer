package com.example.briandemaio.succulenttimer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SucculentAdapter extends BaseAdapter {

    private final Context mContext;
    private final Succulent[] succulents;

    public SucculentAdapter(Context context, Succulent[] succulents){
        this.mContext = context;
        this.succulents = succulents;
    }

    @Override
    public int getCount() {
        return succulents.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Succulent succulent = succulents[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_succulent, null);
        }

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_succulent_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_succulent_name);
        
        imageView.setImageResource(succulent.getImageResource());
        nameTextView.setText(mContext.getString(succulent.getName()));

        return convertView;
    }
}
