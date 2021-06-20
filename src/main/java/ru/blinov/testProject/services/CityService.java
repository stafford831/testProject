package ru.blinov.testProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.blinov.testProject.domain.City;
import ru.blinov.testProject.models.DistanceDto;
import ru.blinov.testProject.repositories.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public List<City> cityList() {
        return cityRepository.findAll();
    }

    public DistanceDto calculate(City from, City to) {

        double firstCityLatitude = from.getLatitude();
        double firstCityLongitude = from.getLongitude();
        double secondCityLatitude = to.getLatitude();
        double secondCityLongitude = to.getLongitude();
        double earthRadius = 6371000;
        double dLat = Math.toRadians(secondCityLatitude - firstCityLatitude);
        double dLng = Math.toRadians(secondCityLongitude - firstCityLongitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(firstCityLatitude)) * Math.cos(Math.toRadians(secondCityLatitude)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return new DistanceDto(from.getName(), to.getName(), dist);
    }

    public List<DistanceDto> calculateDistancesByCitiesLists(List<String> from, List<String> to) {
        List<DistanceDto> result = new ArrayList<>();
        from.forEach(f -> {
            if (ObjectUtils.isEmpty(f))
                return;

            City cityFrom = cityRepository.findCityByName(f);
            if (cityFrom == null)
                return;

            to.forEach(t -> {
                if (ObjectUtils.isEmpty(t))
                    return;

                City cityTo = cityRepository.findCityByName(t);
                if (cityTo == null)
                    return;

                result.add(calculate(cityFrom, cityTo));
            });
        });

        return result;
    }
}
