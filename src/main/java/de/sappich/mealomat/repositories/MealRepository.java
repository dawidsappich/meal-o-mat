package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
