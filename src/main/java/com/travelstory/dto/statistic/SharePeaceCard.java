package com.travelstory.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SharePeaceCard {
    private String label;
    private String fillColor;
    private String strokeColor;
    private String pointColor;
    private String pointStrokeColor;
    private String pointHighlightFill;
    private String pointHighlightStroke;
    private ArrayList<Long> data;

}
