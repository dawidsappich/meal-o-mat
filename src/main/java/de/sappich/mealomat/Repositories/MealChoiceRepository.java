package de.sappich.mealomat.Repositories;

import de.sappich.mealomat.Entities.MealChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealChoiceRepository extends JpaRepository<MealChoice, Long> {
}
