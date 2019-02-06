package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.MealChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealChoiceRepository extends JpaRepository<MealChoice, Long> {
}
