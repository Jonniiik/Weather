package com.eugene.weather.ModelDarkSky;

import java.io.IOException;

public enum PrecipTypeEnum {
    RAIN;

    public String toValue() {
        switch (this) {
            case RAIN:
                return "rain";
        }
        return null;
    }

    public static PrecipTypeEnum forValue(String value) throws IOException {
        if (value.equals("rain")) return RAIN;
        throw new IOException("Cannot deserialize PrecipTypeEnum");
    }
}
