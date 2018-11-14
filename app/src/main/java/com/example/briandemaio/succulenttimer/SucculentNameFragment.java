package com.example.briandemaio.succulenttimer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

public class SucculentNameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.succulent_name_fragment, container, false);
        ImageView succulentImageView = (ImageView)view.findViewById(R.id.succulentImageView);
        EditText editText = (EditText)view.findViewById(R.id.editText);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            succulentImageView.setImageDrawable(Drawable.createFromPath(bundle.getString("imageID")));
        }

        return view;
    }
}
