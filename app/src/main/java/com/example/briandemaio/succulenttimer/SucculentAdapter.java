package com.example.briandemaio.succulenttimer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

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

        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri drawableId = Uri.parse("android.resource://"+mContext.getPackageName()+"/"+imageView.getDrawable().toString());

                Bundle bundle = new Bundle();

                bundle.putString("imageID", String.valueOf(drawableId));

                Fragment nextFragment = new SucculentNameFragment();

                nextFragment.setArguments(bundle);

                android.support.v4.app.FragmentManager manager = ((FragmentActivity)mContext).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.succulent_choice_placeholder, nextFragment);
                transaction.commit();
            }
        });

        return convertView;
    }

}
