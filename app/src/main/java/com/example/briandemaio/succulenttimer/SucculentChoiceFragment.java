package com.example.briandemaio.succulenttimer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class SucculentChoiceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.succulent_choice_fragment, container, false);
        GridView gridView = view.findViewById(R.id.succulent_grid_view);
        SucculentChoiceAdapter succulentChoiceAdapter = new SucculentChoiceAdapter(getActivity(), succulents);
        gridView.setAdapter(succulentChoiceAdapter);
        return view;
    }

    private Succulent[] succulents = {
            new Succulent(R.string.cacti, R.drawable.succulent_1),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_2),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_3),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_4),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_5),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_6),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_7),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_8),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_9),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_10),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_11),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_12),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_13),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_14),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_15),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_16),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_17),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_18),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_19)
    };
}
