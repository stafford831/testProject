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
public class CalculationResultWrapper {
    private CalculationResultType resultType;
    private List<DistanceDto> distances;
}
