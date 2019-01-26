package de.sappich.mealomat.Services;

import de.sappich.mealomat.Entities.MealChoice;
import de.sappich.mealomat.Repositories.MealChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealChoiceService {

    private MealChoiceRepository mealChoiceRepository;

    @Autowired
    public MealChoiceService(MealChoiceRepository mealChoiceRepository) {
        this.mealChoiceRepository = mealChoiceRepository;
    }

    public MealChoice saveMealChoice(MealChoice mealChoice) {
        return this.mealChoiceRepository.save(mealChoice);
    }
}
