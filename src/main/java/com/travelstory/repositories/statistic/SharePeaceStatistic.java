package com.travelstory.repositories.statistic;

import java.util.ArrayList;

public class SharePeaceStatistic {
    private String label;
    private String fillColor;
    private String strokeColor;
    private String pointColor;
    private String pointStrokeColor;
    private String pointHighlightFill;
    private String pointHighlightStroke;
    private ArrayList<Long> data;

    public SharePeaceStatistic() {
    }

    public SharePeaceStatistic(String label, String fillColor, String strokeColor, String pointColor,
                               String pointStrokeColor, String pointHighlightFill, String pointHighlightStroke, ArrayList<Long> data) {
        this.label = label;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.pointColor = pointColor;
        this.pointStrokeColor = pointStrokeColor;
        this.pointHighlightFill = pointHighlightFill;
        this.pointHighlightStroke = pointHighlightStroke;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getPointColor() {
        return pointColor;
    }

    public void setPointColor(String pointColor) {
        this.pointColor = pointColor;
    }

    public String getPointStrokeColor() {
        return pointStrokeColor;
    }

    public void setPointStrokeColor(String pointStrokeColor) {
        this.pointStrokeColor = pointStrokeColor;
    }

    public String getPointHighlightFill() {
        return pointHighlightFill;
    }

    public void setPointHighlightFill(String pointHighlightFill) {
        this.pointHighlightFill = pointHighlightFill;
    }

    public String getPointHighlightStroke() {
        return pointHighlightStroke;
    }

    public void setPointHighlightStroke(String pointHighlightStroke) {
        this.pointHighlightStroke = pointHighlightStroke;
    }

    public ArrayList<Long> getData() {
        return data;
    }

    public void setData(ArrayList<Long> data) {
        this.data = data;
    }

}
