package com.eugene.weather.Model;

public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    public Weather() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescreption() {
        return description;

    }


    public void setDescreption(String descreption) {
        this.description = descreption;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
