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
        String[] succlentTypes = getResources().getStringArray(R.array.succulent_types_array);

        Succulent[] succulents={
                new Succulent(succlentTypes[0], R.drawable.succulent_1),
                new Succulent(succlentTypes[1], R.drawable.succulent_2),
                new Succulent(succlentTypes[2], R.drawable.succulent_3),
                new Succulent(succlentTypes[3], R.drawable.succulent_4),
                new Succulent(succlentTypes[4], R.drawable.succulent_5),
                new Succulent(succlentTypes[5], R.drawable.succulent_6),
                new Succulent(succlentTypes[6], R.drawable.succulent_7),
                new Succulent(succlentTypes[7], R.drawable.succulent_8),
                new Succulent(succlentTypes[9], R.drawable.succulent_10),
                new Succulent(succlentTypes[10], R.drawable.succulent_11),
                new Succulent(succlentTypes[11], R.drawable.succulent_12),
                new Succulent(succlentTypes[12], R.drawable.succulent_13),
                new Succulent(succlentTypes[13], R.drawable.succulent_14),
                new Succulent(succlentTypes[13], R.drawable.succulent_15),
                new Succulent(succlentTypes[13], R.drawable.succulent_15)
        };

        SucculentChoiceAdapter succulentChoiceAdapter = new SucculentChoiceAdapter(getActivity(), succulents);
        gridView.setAdapter(succulentChoiceAdapter);
        return view;
    }
}
