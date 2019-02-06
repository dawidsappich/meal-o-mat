package de.sappich.mealomat.api;

import de.sappich.mealomat.api.viewmodel.MealViewModel;
import de.sappich.mealomat.entities.MealChoice;
import de.sappich.mealomat.entities.StoreLocation;
import de.sappich.mealomat.repositories.MealChoiceRepository;
import de.sappich.mealomat.utils.ApplicationCode;
import de.sappich.mealomat.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("meal")
public class MealController {

    @Autowired
    private MealChoiceRepository repository;

    @GetMapping
    public Collection<MealChoice> getAllMeals() {
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createMeal(@RequestBody MealViewModel mealViewModel) {

        StoreLocation location = mealViewModel.getLocation();

        MealChoice meal = new MealChoice();
        meal.setDisplayName(mealViewModel.getDisplayName());
        meal.setName(mealViewModel.getName());
        meal.setStoreLocation(location);

        repository.save(meal);

        ApplicationResponse response = createResponse("meal created", ApplicationCode.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{mealId}")
    public ResponseEntity<ApplicationResponse> deleteOneMeal(@PathVariable Long mealId) {

        Optional<ResponseEntity> result = checkIfExists(mealId);
        if (result.isPresent()) {
            return result.get();
        }

        this.repository.deleteById(mealId);
        ApplicationResponse response = createResponse("meal deleted", ApplicationCode.OK);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping
    public ResponseEntity<ApplicationResponse> deleteAllMeals() {
        this.repository.deleteAllInBatch();
        ApplicationResponse response = createResponse("all meals deleted!", ApplicationCode.OK);
        return ResponseEntity.ok(response);
    }

    public ApplicationResponse createResponse(String message, ApplicationCode code) {
        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        boolean isSuccess = false;

        if (code.equals(ApplicationCode.OK)) {
            isSuccess = true;
        }

        return builder.setMessage(message)
                .setIsSuccess(isSuccess)
                .setCode(code)
                .setTime(LocalDateTime.now())
                .build();
    }

    @PatchMapping
    public ResponseEntity<ApplicationResponse> updateMeal(@RequestBody MealChoice mealChoice) {
        // TODO: Validate input with Validator
        Optional<ResponseEntity> result = checkIfExists(mealChoice.getId());
        if (result.isPresent()) {
            return result.get();
        }

        this.repository.save(mealChoice);
        ApplicationResponse response = createResponse("meal updated", ApplicationCode.OK);
        return ResponseEntity.ok(response);
    }

    public Optional<ResponseEntity> checkIfExists(Long mealId) {
        //check if meal exists
        Optional<MealChoice> foundMeal = this.repository.findById(mealId);
        // when not found than error
        if (foundMeal.isEmpty()) {
            ApplicationResponse response = createResponse(String.format("meal with id %d not found", mealId), ApplicationCode.ERROR);
            return Optional.of(ResponseEntity.badRequest().body(response));
        }
        return Optional.empty();
    }


}
