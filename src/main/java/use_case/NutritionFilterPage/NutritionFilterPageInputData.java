package use_case.NutritionFilterPage;

import entity.Nutrient;

import java.util.List;

/**
 * The input data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageInputData {

    private final List<Nutrient> selectedNutrients;

    public NutritionFilterPageInputData(List<Nutrient> selectedNutrients) {
        this.selectedNutrients = selectedNutrients;
    }

    public List<Nutrient> getSelectedNutrients() {
        return selectedNutrients;
    }
}
