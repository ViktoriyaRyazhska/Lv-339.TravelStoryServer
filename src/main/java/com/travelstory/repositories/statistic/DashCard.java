package com.travelstory.repositories.statistic;

import com.google.gson.Gson;

public class DashCard {
    private String colorDark;
    private String colorLight;
    private Long number;
    private String title;
    private String icon;


    public DashCard() {
    }

    public DashCard(String colorDark, String colorLight, Long number, String title, String icon) {
        this.colorDark = colorDark;
        this.colorLight = colorLight;
        this.number = number;
        this.title = title;
        this.icon = icon;
    }

    public String getColorDark() {
        return colorDark;
    }

    public void setColorDark(String colorDark) {
        this.colorDark = colorDark;
    }

    public String getColorLight() {
        return colorLight;
    }

    public void setColorLight(String colorLight) {
        this.colorLight = colorLight;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "DashCard{" +
                "colorDark='" + colorDark + '\'' +
                ", colorLight='" + colorLight + '\'' +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
