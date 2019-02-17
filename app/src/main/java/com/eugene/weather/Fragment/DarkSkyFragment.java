package com.eugene.weather.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eugene.weather.ModelDarkSky.WeatherResultDarkSky;
import com.eugene.weather.R;
import com.eugene.weather.Retrofit.IDarkSky;
import com.eugene.weather.Retrofit.RetrifitClientDarkSky;
import com.eugene.weather.Сommon.Common;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DarkSkyFragment extends Fragment {
    TextView temperatureDarkTextView, cityNameDarkTextView;
    CompositeDisposable compositeDisposable;
    IDarkSky mService;

    public DarkSkyFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofitDarkSky = RetrifitClientDarkSky.getRetrofitDarkSky();
        mService = retrofitDarkSky.create(IDarkSky.class);
    }

    static DarkSkyFragment instance;

    public static DarkSkyFragment getInstance() {
        if (instance == null) {
            instance = new DarkSkyFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tomorrow, container, false);

        temperatureDarkTextView = (TextView) view.findViewById(R.id.temperatureDarkTextView);
        cityNameDarkTextView = (TextView) view.findViewById(R.id.cityNameDarkTextView);

        getWeatherInfoDarkSky();

        return view;
    }

    private void getWeatherInfoDarkSky() {
//        compositeDisposable.add(mService.getWeatherDarkSkyByLatitudeLongitude(String.valueOf(Common.current_location.getLatitude()),
//                String.valueOf(Common.current_location.getLongitude()), Common.DarkSky_ID, "coord")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<WeatherResultDarkSky>() {
//                    @Override
//                    public void accept(WeatherResultDarkSky weatherResultDarkSky) throws Exception {
//                        cityNameDarkTextView.setText(weatherResultDarkSky.getTimezone());
//                        temperatureDarkTextView.setText(new StringBuilder(String.valueOf(weatherResultDarkSky.getCurrently().getTemperature())).append(" °C").toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                })
//        );
    }

    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
