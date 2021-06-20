package ru.blinov.testProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistanceDto {
    private String fromCity;
    private String toCity;
    private double distance;
}
