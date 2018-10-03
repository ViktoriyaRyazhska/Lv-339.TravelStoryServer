package com.travelstory.repositories.statistic;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class RegistrationStatistic {
    @Autowired
    private UserStatistic userStatistic;

    private String backgroundColor;
    private String borderColor;
    private ArrayList<Integer> data;
    private String label;
    private String fill;

    public RegistrationStatistic() {
    }

    public RegistrationStatistic(String backgroundColor, String borderColor, String label, String fill,
                                 ArrayList<Integer> data) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.label = label;
        this.fill = fill;
        this.data = data;

    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
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

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

}
