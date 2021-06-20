package ru.blinov.testProject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.blinov.testProject.models.*;
import ru.blinov.testProject.services.CityService;
import ru.blinov.testProject.services.DistanceServices;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DistanceController {
    private DistanceServices distanceServices;
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity findCities() {
        return new ResponseEntity<>(cityService.cityList(), HttpStatus.OK);
    }

    @GetMapping("/calculate")
    public ResponseEntity calculateDistance(@RequestBody CalculateDistanceRequest request) {
        CalculationDistanceResponse response = new CalculationDistanceResponse();
        response.setResults(new ArrayList<>());
        CalculationType calculationType = request.getCalculationType();
        switch (calculationType) {
            case CROWFLIGHT:
                response.getResults().add(new CalculationResultWrapper(CalculationResultType.CROWFLIGHT, cityService.calculateDistancesByCitiesLists(request.getFromCity(), request.getToCity())));
                break;
            case DISTANCE_MATRIX:
                response.getResults().add(new CalculationResultWrapper(CalculationResultType.DISTANCE_MATRIX, distanceServices.findDistancesByFromToLists(request.getFromCity(), request.getToCity())));
                break;
            case ALL:
                response.getResults().add(new CalculationResultWrapper(CalculationResultType.CROWFLIGHT, cityService.calculateDistancesByCitiesLists(request.getFromCity(), request.getToCity())));
                response.getResults().add(new CalculationResultWrapper(CalculationResultType.DISTANCE_MATRIX, distanceServices.findDistancesByFromToLists(request.getFromCity(), request.getToCity())));
                break;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
