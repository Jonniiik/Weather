package com.eugene.weather.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eugene.weather.Adapter.WeatherForecastAdapter;
import com.eugene.weather.Model.WeatherForecastResult;
import com.eugene.weather.Model.WeatherResult;
import com.eugene.weather.R;
import com.eugene.weather.Retrofit.IOpenWeatherMap;
import com.eugene.weather.Retrofit.RetrofitClient;
import com.eugene.weather.Сommon.Common;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ImageView imageWeather;
    TextView cityName, textTemperature, textDescriotion, textDataTime, textWind, textPressure, textHumidity, textSunrise, textSunset, textGeoCords;
    LinearLayout linerLayoutWeatherPanel;
    ProgressBar loading;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    RecyclerView recyclerViewForecast;

    private SwipeRefreshLayout swipeRefreshToday;

    static TodayFragment instance;

    public static TodayFragment getInstance() {
        if (instance == null) {
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

        recyclerViewForecast = (RecyclerView) view.findViewById(R.id.recyclerViewForecast);
        recyclerViewForecast.setHasFixedSize(true);
        recyclerViewForecast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        swipeRefreshToday = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshToday);
        swipeRefreshToday.setOnRefreshListener(this);

        getWeatherInfo();
        getForecastInfo();

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

                        //image add
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString()).into(imageWeather);


                        //information weather
                        cityName.setText(weatherResult.getName());
                        Log.e("Test", "cityName");
                        textWind.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append(" m/s").toString());
                        Log.e("Test", "Wind");
                        textDescriotion.setText(new StringBuilder("Погода в ").append(weatherResult.getName()).toString());
                        Log.e("Test", "Descriotion");
                        textTemperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                        Log.e("Test", "Temperature");
                        textDataTime.setText(Common.convertUnixToDate(weatherResult.getDt()));
                        Log.e("Test", "DataTime");
                        textPressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append(" hpa").toString());
                        Log.e("Test", "Pressure");
                        textHumidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append(" %").toString());
                        Log.e("Test", "Humidity");
                        textSunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                        Log.e("Test", "Sunrise");
                        textSunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));
                        Log.e("Test", "Sunset");
                        textGeoCords.setText(new StringBuilder(weatherResult.getCoord().toString()).toString());
                        Log.e("Test", "GeoCords");

                        //panel
                        linerLayoutWeatherPanel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private void getForecastInfo() {
        compositeDisposable.add(mService.getForecastWeatherByLatlng(
                String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {
                    @Override
                    public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                        displayForecastWeather(weatherForecastResult);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ERROR", "" + throwable.getMessage());


                    }
                })
        );

    }

    private void displayForecastWeather(WeatherForecastResult weatherForecastResult) {

        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(), weatherForecastResult);
        recyclerViewForecast.setAdapter(adapter);

    }

    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshToday.setRefreshing(false);
                // Common.current_location = null;
                getWeatherInfo();
                getForecastInfo();
            }
        }, 5000);
    }
}
