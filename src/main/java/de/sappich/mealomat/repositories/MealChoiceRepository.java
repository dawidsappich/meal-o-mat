package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.MealChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealChoiceRepository extends JpaRepository<MealChoice, Long> {
}
