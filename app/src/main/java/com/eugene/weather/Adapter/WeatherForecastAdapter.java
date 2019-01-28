package com.eugene.weather.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugene.weather.Model.WeatherForecastResult;
import com.eugene.weather.R;
import com.eugene.weather.Сommon.Common;
import com.squareup.picasso.Picasso;

import static java.lang.String.valueOf;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    Context context;
    WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_forecast, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherForecastResult.list.get(i).weather.get(0).getIcon())
                .append(".png").toString()).into(holder.imageWeather);

        holder.dataName.setText(new StringBuilder(Common.convertUnixToDate2(weatherForecastResult.list.get(i).dt)));

        //holder.textTemperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(i).main.getTemp())).append(" °C "));
        holder.textTemperature.setText(new StringBuilder(valueOf(Integer.valueOf((int) weatherForecastResult.list.get(i).main.getTemp()))).append(" °C "));

        holder.textDescriptions.setText(new StringBuffer(String.valueOf(weatherForecastResult.list.get(0).weather.get(0).getDescreption())));
    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dataName, textTemperature, textCityName, textDescriptions;
        ImageView imageWeather;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            imageWeather = (ImageView) itemView.findViewById(R.id.imageWeather);
            dataName = (TextView) itemView.findViewById(R.id.dataName);
            textTemperature = (TextView) itemView.findViewById(R.id.textTemperature);
            textDescriptions = (TextView) itemView.findViewById(R.id.textDescriptions);

        }
    }
}
