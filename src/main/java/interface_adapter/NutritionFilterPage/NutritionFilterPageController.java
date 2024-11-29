package interface_adapter.NutritionFilterPage;

import use_case.NutritionFilterPage.NutritionFilterPageInputBoundrary;
import use_case.NutritionFilterPage.NutritionFilterPageInputData;

import java.util.List;

/**
 * The Controller for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageController {
    private final NutritionFilterPageInputBoundrary interactor;

    public NutritionFilterPageController(NutritionFilterPageInputBoundrary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the Filter Based on Nutrition Use Case.
     * @param selectedNutrients the selected nutrients
     */
    public void execute(List<String> selectedNutrients) {
        if (selectedNutrients == null || selectedNutrients.isEmpty()) {
            throw new IllegalArgumentException("Selected nutrients cannot be null or empty.");
        }

        final NutritionFilterPageInputData nutritionFilterPageInputData = new NutritionFilterPageInputData(
                selectedNutrients);

        interactor.execute(nutritionFilterPageInputData);
    }

    /**
     * Switches to the NutritionFilterPage.
     */
    public void switchToNutritionFilterPage(String username) {
        interactor.switchToNutritionFilterPage(username);
    }

    public void switchToHomepage(String username) {
        interactor.switchToHomepage(username);
    }
}
