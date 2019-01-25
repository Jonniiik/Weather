package com.eugene.weather.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eugene.weather.Adapter.WeatherForecastAdapter;
import com.eugene.weather.Model.WeatherForecastResult;
import com.eugene.weather.R;
import com.eugene.weather.Retrofit.IOpenWeatherMap;
import com.eugene.weather.Retrofit.RetrofitClient;
import com.eugene.weather.Сommon.Common;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TomorrowFragment extends Fragment {
    Button resetLocation;


    public TomorrowFragment() {

    }

    static TomorrowFragment instance;

    public static TomorrowFragment getInstance() {
        if (instance == null) {
            instance = new TomorrowFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tomorrow, container, false);

        resetLocation = (Button) view.findViewById(R.id.resetLocation);
        resetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.current_location.reset();
                Log.e("Reset", "Reset");
            }
        });

        return view;
    }
}
