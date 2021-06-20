package ru.blinov.testProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.blinov.testProject.domain.Distance;
import ru.blinov.testProject.models.DistanceDto;
import ru.blinov.testProject.repositories.DistanceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistanceServices {
    private DistanceRepository distanceRepository;

    public Distance findDistanceByCitiesName(String firstCity, String secondCity) {
        return distanceRepository.findByFromCityAndToCity(firstCity, secondCity);
    }

    public List<DistanceDto> findDistancesByFromToLists(List<String> from, List<String> to) {
        List<DistanceDto> result = new ArrayList<>();

        from.forEach(f -> {
            if (ObjectUtils.isEmpty(f))
                return;

            to.forEach(t -> {
                if (ObjectUtils.isEmpty(t))
                    return;

                Distance distance = findDistanceByCitiesName(f, t);
                if (distance == null)
                    return;

                result.add(new DistanceDto(distance.getFromCity(), distance.getToCity(), distance.getDistance()));
            });
        });

        return result;
    }
}
