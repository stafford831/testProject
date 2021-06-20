package ru.blinov.testProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.blinov.testProject.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findCityByName(String name);
}
