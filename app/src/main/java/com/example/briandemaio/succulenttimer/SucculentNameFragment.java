package com.example.briandemaio.succulenttimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.briandemaio.succulenttimer.ChoiceActivity.EXTRA_REPLY;

public class SucculentNameFragment extends Fragment {

    private EditText mEditWordView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.succulent_name_fragment, container, false);
        ImageView succulentImageView = view.findViewById(R.id.succulentImageView);
        mEditWordView = view.findViewById(R.id.editText);
        final Bundle bundle = this.getArguments();

        if(bundle != null){
            Glide.with(getContext()).load(bundle.getInt("imageID")).into(succulentImageView);
        }

        final Button button = view.findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    getActivity().setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra("imageID", bundle.getInt("imageID"));
                    getActivity().setResult(RESULT_OK, replyIntent);
                }
                getActivity().finish();
            }
        });
        setRetainInstance(true);
        return view;
    }
}
