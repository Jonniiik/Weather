package com.eugene.weather.ModelDarkSky;

import java.io.IOException;

public enum  Summary {
    MOSTLY_CLOUDY, OVERCAST, PARTLY_CLOUDY;

    public String toValue() {
        switch (this) {
            case MOSTLY_CLOUDY: return "Mostly Cloudy";
            case OVERCAST: return "Overcast";
            case PARTLY_CLOUDY: return "Partly Cloudy";
        }
        return null;
    }


    public static Summary forValue(String value) throws IOException {
        if (value.equals("Mostly Cloudy")) return MOSTLY_CLOUDY;
        if (value.equals("Overcast")) return OVERCAST;
        if (value.equals("Partly Cloudy")) return PARTLY_CLOUDY;
        throw new IOException("Cannot deserialize Summary");
    }

}
