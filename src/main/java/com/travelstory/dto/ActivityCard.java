package com.travelstory.repositories.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ActivityCard {
    private String backgroundColor;
    private String borderColor;
    private ArrayList<Long> data;
    private String label;
    private Boolean fill;

    @Override
    public String toString() {
        return "{" + "backgroundColor='" + backgroundColor + '\'' + ", borderColor='" + borderColor + '\'' + ", data="
                + data + ", label='" + label + '\'' + ", fill=" + fill + '}';
    }
}
