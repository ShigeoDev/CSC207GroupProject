package use_case.NutritionFilterPage;

import entity.Nutrient;

import java.util.List;

/**
 * The input data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageInputData {

    private final List<String> selectedNutrients;

    public NutritionFilterPageInputData(List<String> selectedNutrients) {
        this.selectedNutrients = selectedNutrients;
    }

    public List<String> getSelectedNutrients() {
        return selectedNutrients;
    }
}
