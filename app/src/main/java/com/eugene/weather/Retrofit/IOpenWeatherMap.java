package com.eugene.weather.Retrofit;



import com.eugene.weather.Model.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET("weather")
    Observable<WeatherResult> getWeatherByLatlng(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("appid") String addip,
                                                 @Query("units") String units);
}
