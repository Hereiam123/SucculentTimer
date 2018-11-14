package com.example.briandemaio.succulenttimer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(String.valueOf(position));
        return dummyTextView;
    }
}
