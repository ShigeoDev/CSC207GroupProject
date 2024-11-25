package use_case.NutritionFilterPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The input data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageInputData {

    private final List<String> selectedNutrients;

    public NutritionFilterPageInputData(List<String> selectedNutrients) {
        if (selectedNutrients == null || selectedNutrients.isEmpty()) {
            throw new IllegalArgumentException("Selected nutrients cannot be null or empty.");
        }
        this.selectedNutrients = Collections.unmodifiableList(new ArrayList<>(selectedNutrients));
    }

    public List<String> getSelectedNutrients() {
        return selectedNutrients;
    }
}
