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

    /**
     * Retrieves the list of nutrients selected by the user.
     * @return a {@code List<String>} containing the names of the selected nutrients; may be empty but not {@code null}
     */
    public List<String> getSelectedNutrients() {
        return selectedNutrients;
    }
}
