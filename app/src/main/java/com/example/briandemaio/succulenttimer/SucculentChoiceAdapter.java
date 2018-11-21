package com.example.briandemaio.succulenttimer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SucculentChoiceAdapter extends BaseAdapter {

    private final Context mContext;
    private final Succulent[] succulents;

    public SucculentChoiceAdapter(Context context, Succulent[] succulents){
        this.mContext = context;
        this.succulents = succulents;
    }

    @Override
    public int getCount() {
        return succulents.length;
    }

    @Override
    public Object getItem(int position) {
        return succulents[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Succulent succulent = succulents[position];

        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.choice_view_item, null);

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.succulent_image);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.succulent_name_text);

        Glide.with(mContext).load(succulent.getImageResource()).into(imageView);
        nameTextView.setText(mContext.getString(succulent.getNameId()));

        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putInt("imageID", succulent.getImageResource());

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
