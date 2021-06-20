package ru.blinov.testProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.blinov.testProject.domain.Distance;


@Repository
public interface DistanceRepository extends JpaRepository<Distance, Integer> {
    Distance findByFromCityAndToCity(String departmentCity, String arrivalCity);
}
