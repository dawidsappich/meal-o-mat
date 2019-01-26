package de.sappich.mealomat.Repositories;

import de.sappich.mealomat.Entities.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, String> {
}
