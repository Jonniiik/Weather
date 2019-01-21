package com.eugene.weather.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TomorrowFragment extends Fragment {


    public TomorrowFragment() {
        // Required empty public constructor
    }
    static TomorrowFragment instance;

    public static TomorrowFragment getInstance() {
        if (instance == null){
            instance = new TomorrowFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tomorrow, container, false);
    }

}
