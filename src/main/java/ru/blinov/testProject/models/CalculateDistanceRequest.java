package ru.blinov.testProject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateDistanceRequest {
    private CalculationType calculationType;
    private List<String> fromCity;
    private List<String> toCity;
}
