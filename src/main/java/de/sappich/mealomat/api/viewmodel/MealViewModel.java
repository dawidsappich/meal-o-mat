package de.sappich.mealomat.api.viewmodel;

import de.sappich.mealomat.entities.StoreLocation;
import lombok.Data;

@Data
public class MealViewModel {
    private Long id;
    private String name;
    private String displayName;
    private StoreLocation location;
}
