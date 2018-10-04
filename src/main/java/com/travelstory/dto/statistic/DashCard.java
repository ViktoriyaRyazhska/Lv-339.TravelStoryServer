package com.travelstory.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashCard {
    private String colorDark;
    private String colorLight;
    private Long number;
    private String title;
    private String icon;

    @Override
    public String toString() {
        return "{" + "colorDark='" + colorDark + '\'' + ", colorLight='" + colorLight + '\'' + ", number=" + number
                + ", title='" + title + '\'' + ", icon='" + icon + '\'' + '}';
    }
}
