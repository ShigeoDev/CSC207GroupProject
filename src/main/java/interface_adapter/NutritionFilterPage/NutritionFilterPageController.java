package interface_adapter.NutritionFilterPage;

import entity.Nutrient;
import use_case.NutritionFilterPage.NutritionFilterPageInputBoundrary;
import use_case.NutritionFilterPage.NutritionFilterPageInputData;

import java.util.List;

/**
 * The Controller for the Filter Based on Nutrition Use Case.
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
    public void execute(List<String> selectedNutrients) {
        final NutritionFilterPageInputData nutritionFilterPageInputData = new NutritionFilterPageInputData(
                selectedNutrients);

        filterBasedOnNutritionUseCaseInteractor.execute(nutritionFilterPageInputData);
    }

}
