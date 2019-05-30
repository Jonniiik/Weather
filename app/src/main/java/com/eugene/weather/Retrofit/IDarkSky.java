package com.eugene.weather.Retrofit;

import com.eugene.weather.ModelDarkSky.WeatherResultDarkSky;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDarkSky {

    @GET("coord")
    Observable<WeatherResultDarkSky> getWeatherDarkSkyByLatitudeLongitude(
                                                    @Query("latitude") String latitude,
                                                    @Query("longitude") String longitude);
}
