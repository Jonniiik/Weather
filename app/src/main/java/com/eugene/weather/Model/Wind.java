package com.eugene.weather.Model;

public class Wind {
    private double speed;
    private double deg;

    public Wind() {
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

//    public String toString(){
//        return new StringBuilder("").append(this.deg).append(',').append(this.speed).toString();
//    }
}
