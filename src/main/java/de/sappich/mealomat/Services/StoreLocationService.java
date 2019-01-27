package de.sappich.mealomat.Services;

import de.sappich.mealomat.Entities.StoreLocation;
import de.sappich.mealomat.Repositories.StoreLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreLocationService {

    private StoreLocationRepository storeLocationRepository;

    @Autowired
    public StoreLocationService(StoreLocationRepository storeLocationRepository) {
        this.storeLocationRepository = storeLocationRepository;
    }

    public StoreLocation findStore(Long storeId) {
        Optional<StoreLocation> foundStoreLocation = this.storeLocationRepository.findById(storeId);
        return foundStoreLocation.get();
    }
}
