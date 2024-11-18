package interface_adapter.NutritionFilterPage;

import entity.Nutrient;
import use_case.NutritionFilterPage.NutritionFilterPageInputBoundrary;
import use_case.NutritionFilterPage.NutritionFilterPageInputData;

import java.util.List;

/**
 * Controller for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageController {
    private final NutritionFilterPageInputBoundrary filterBasedOnNutritionUseCaseInteractor;

    public NutritionFilterPageController(NutritionFilterPageInputBoundrary filterBasedOnNutritionUseCaseInteractor) {
        this.filterBasedOnNutritionUseCaseInteractor = filterBasedOnNutritionUseCaseInteractor;
    }

    /**
     * Executes the Filter Based on Nutrition Use Case.
     * @param selectedNutrients the selected nutrients
     */
    public void execute(List<Nutrient> selectedNutrients) {
        final NutritionFilterPageInputData nutritionFilterPageInputData = new NutritionFilterPageInputData(
                selectedNutrients);

        filterBasedOnNutritionUseCaseInteractor.execute(nutritionFilterPageInputData);
    }

}
