package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.MealChoice;
import de.sappich.mealomat.Entities.StoreLocation;
import de.sappich.mealomat.Services.MealChoiceService;
import de.sappich.mealomat.Services.StoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MealChoiceController {

    private MealChoiceService mealChoiceService;
    private StoreLocationService storeLocationService;

    public MealChoiceController(MealChoiceService mealChoiceService, StoreLocationService storeLocationService) {
        this.mealChoiceService = mealChoiceService;
        this.storeLocationService = storeLocationService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MealChoice addMeal(@RequestBody MealChoice meal) {
        StoreLocation foundStore = this.storeLocationService.findStore("7e5209a5-9161-4aa0-9014-0c9b4e39a926");
        MealChoice mealChoice = new MealChoice();
        mealChoice.setDisplayName("Yam Yam");
        mealChoice.setMealId(UUID.randomUUID().toString());
        mealChoice.setStoreLocation(foundStore);
        return this.mealChoiceService.saveMealChoice(mealChoice);
    }
}
