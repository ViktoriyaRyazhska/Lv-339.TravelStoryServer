package com.travelstory.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCard {

    private String backgroundColor;
    private String borderColor;
    private String label;
    private String fill;
    private ArrayList<Integer> data;

}
