package com.eugene.weather.ModelDarkSky;

public class Daily {
    private String summary;
    private PrecipTypeEnum icon;
    private DailyDatum[] data;

    public Daily() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PrecipTypeEnum getIcon() {
        return icon;
    }

    public void setIcon(PrecipTypeEnum icon) {
        this.icon = icon;
    }

    public DailyDatum[] getData() {
        return data;
    }

    public void setData(DailyDatum[] data) {
        this.data = data;
    }
}
