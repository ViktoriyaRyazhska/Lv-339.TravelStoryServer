package com.travelstory.repositories.statistic;

import java.util.ArrayList;

public class ActivityStatistic {
    private String backgroundColor;
    private String borderColor;
    private ArrayList<Long> data;
    private String label;
    private Boolean fill;

    public ActivityStatistic() {
    }

    public ActivityStatistic(String backgroundColor, String borderColor, ArrayList<Long> data, String label,
                             Boolean fill) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.data = data;
        this.label = label;
        this.fill = fill;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public ArrayList<Long> getData() {
        return data;
    }

    public void setData(ArrayList<Long> data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getFill() {
        return fill;
    }

    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    @Override
    public String toString() {
        return "{" + "backgroundColor='" + backgroundColor + '\'' + ", borderColor='" + borderColor + '\'' + ", data="
                + data + ", label='" + label + '\'' + ", fill=" + fill + '}';
    }
}
