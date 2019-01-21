package com.eugene.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eugene.weather.Model.WeatherResult;
import com.eugene.weather.Retrofit.IOpenWeatherMap;
import com.eugene.weather.Retrofit.RetrofitClient;
import com.eugene.weather.Сommon.Common;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayFragment extends Fragment {

    ImageView imageWeather;
    TextView cityName, textTemperature, textDescriotion, textDataTime, textWind, textPressure, textHumidity, textSunrise, textSunset, textGeoCords;
    LinearLayout linerLayoutWeatherPanel;
    ProgressBar loading;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static TodayFragment instance;

    public static TodayFragment getInstance() {
        if (instance == null){
            instance = new TodayFragment();
        }
        return instance;
    }

    public TodayFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        imageWeather = (ImageView) view.findViewById(R.id.imageWeather);

        cityName = (TextView) view.findViewById(R.id.cityName);
        textTemperature = (TextView) view.findViewById(R.id.textTemperature);
        textDescriotion = (TextView) view.findViewById(R.id.textDescriotion);
        textDataTime = (TextView) view.findViewById(R.id.textDataTime);
        textWind = (TextView) view.findViewById(R.id.textWind);
        textPressure = (TextView) view.findViewById(R.id.textPressure);
        textHumidity = (TextView) view.findViewById(R.id.textHumidity);
        textSunrise = (TextView) view.findViewById(R.id.textSunrise);
        textSunset = (TextView) view.findViewById(R.id.textSunset);
        textGeoCords = (TextView) view.findViewById(R.id.textGeoCords);

        linerLayoutWeatherPanel = (LinearLayout) view.findViewById(R.id.linerLayoutWeatherPanel);

        loading = (ProgressBar) view.findViewById(R.id.loading);

        getWeatherInfo();

        return view;
    }

    private void getWeatherInfo() {
        compositeDisposable.add(mService.getWeatherByLatlng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<WeatherResult>() {
            @Override
            public void accept(WeatherResult weatherResult) throws Exception {

                //image
                Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherResult.getWeather().get(0).getIcon())
                .append(".png").toString()).into(imageWeather);


                //information
                cityName.setText(weatherResult.getName());
                textDescriotion.setText(new StringBuilder("Погода в ").append(weatherResult.getName()).toString());
                textTemperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                textDataTime.setText(Common.convertUnixToDate(weatherResult.getDt()));
                textPressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append(" hra").toString());
                textHumidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append(" %").toString());
                textSunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                textSunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));
                textGeoCords.setText(new StringBuilder(weatherResult.getCoord().toString()).toString());


                //panel
                linerLayoutWeatherPanel.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(getActivity(), ""+ throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        );
    }

}
