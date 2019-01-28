package com.eugene.weather.ModelDarkSky;

public class Hourly {
    private String summary;
    private HourlyIcon icon;
    private Currently[] data;

    public Hourly() {
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

    public Currently[] getData() {
        return data;
    }

    public void setData(Currently[] data) {
        this.data = data;
    }
}
