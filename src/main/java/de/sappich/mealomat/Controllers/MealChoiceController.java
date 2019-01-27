package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Services.MealChoiceService;
import de.sappich.mealomat.Services.StoreLocationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealChoiceController {

    private MealChoiceService mealChoiceService;
    private StoreLocationService storeLocationService;

    public MealChoiceController(MealChoiceService mealChoiceService, StoreLocationService storeLocationService) {
        this.mealChoiceService = mealChoiceService;
        this.storeLocationService = storeLocationService;
    }

}
