package com.eugene.weather.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrifitClientDarkSky {
    private static Retrofit retrofitDarkSky;

    public static Retrofit getRetrofitDarkSky(){
        if (retrofitDarkSky == null)
            retrofitDarkSky = new Retrofit.Builder()
                    .baseUrl("https://api.darksky.net/forecast/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofitDarkSky;
    }
}
