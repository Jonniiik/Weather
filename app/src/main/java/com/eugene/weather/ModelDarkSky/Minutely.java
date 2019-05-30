package com.eugene.weather.ModelDarkSky;

public class Minutely {
    private String summary;
    private HourlyIcon icon;
    private MinutelyDatum[] data;

    public Minutely() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public HourlyIcon getIcon() {
        return icon;
    }

    public void setIcon(HourlyIcon icon) {
        this.icon = icon;
    }

    public MinutelyDatum[] getData() {
        return data;
    }

    public void setData(MinutelyDatum[] data) {
        this.data = data;
    }
}
