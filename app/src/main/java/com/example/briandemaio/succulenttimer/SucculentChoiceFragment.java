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

        String cacti = getResources().getString(R.string.cacti);
        String aloeVera = getResources().getString(R.string.aloe_vera);

        Succulent[] succulents = {
                new Succulent(cacti, R.drawable.succulent_1),
                new Succulent(aloeVera, R.drawable.succulent_2),
                new Succulent(aloeVera, R.drawable.succulent_3),
                new Succulent(aloeVera, R.drawable.succulent_4),
                new Succulent(aloeVera, R.drawable.succulent_5),
                new Succulent(aloeVera, R.drawable.succulent_6),
                new Succulent(aloeVera, R.drawable.succulent_7),
                new Succulent(aloeVera, R.drawable.succulent_8),
                new Succulent(aloeVera, R.drawable.succulent_9),
                new Succulent(aloeVera, R.drawable.succulent_10),
                new Succulent(aloeVera, R.drawable.succulent_11),
                new Succulent(aloeVera, R.drawable.succulent_12),
                new Succulent(aloeVera, R.drawable.succulent_13),
                new Succulent(aloeVera, R.drawable.succulent_14),
                new Succulent(aloeVera, R.drawable.succulent_15),
                new Succulent(aloeVera, R.drawable.succulent_16),
                new Succulent(aloeVera, R.drawable.succulent_17),
                new Succulent(aloeVera, R.drawable.succulent_18),
                new Succulent(aloeVera, R.drawable.succulent_19)
        };

        SucculentChoiceAdapter succulentChoiceAdapter = new SucculentChoiceAdapter(getActivity(), succulents);
        gridView.setAdapter(succulentChoiceAdapter);
        return view;
    }
}
