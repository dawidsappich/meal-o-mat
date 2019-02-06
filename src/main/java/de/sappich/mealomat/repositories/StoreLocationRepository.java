package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, Long> {
}
