package com.travelstory.repositories.statistic;

public class CardStatistic {
    private String title;
    private Long current;
    private Integer max;
    private String background;
    private String color;
    private String boxcolor;

    public CardStatistic() {
    }

    public CardStatistic(String title, Long current, Integer max, String background, String color, String boxcolor) {
        this.title = title;
        this.current = current;
        this.max = max;
        this.background = background;
        this.color = color;
        this.boxcolor = boxcolor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBoxcolor() {
        return boxcolor;
    }

    public void setBoxcolor(String boxcolor) {
        this.boxcolor = boxcolor;
    }

    @Override
    public String toString() {
        return "{" + "title='" + title + '\'' + ", current=" + current + ", max=" + max + ", background='" + background
                + '\'' + ", color='" + color + '\'' + ", boxcolor='" + boxcolor + '\'' + '}';
    }

}
