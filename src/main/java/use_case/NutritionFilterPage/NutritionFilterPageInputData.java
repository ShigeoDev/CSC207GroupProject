package use_case.NutritionFilterPage;

import java.util.ArrayList;

/**
 * The input data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageInputData {

    private final ArrayList<String> selectedNutrients;

    /**
     * Constructs a new {@code NutritionFilterPageInputData} object with the specified list of selected nutrients.
     * <p>
     * The constructor accepts an {@code ArrayList<String>} containing the names of nutrients that the user
     * has selected for filtering purposes. The provided list must not be {@code null} or empty.
     * If the list is {@code null} or empty, an {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @param selectedNutrients the {@code ArrayList<String>} of nutrient names selected by the user
     * @throws IllegalArgumentException if {@code selectedNutrients} is {@code null} or empty
     */
    public NutritionFilterPageInputData(ArrayList<String> selectedNutrients) {
        this.selectedNutrients = selectedNutrients;
    }

    /**
     * Retrieves the list of nutrients selected by the user.
     * @return a {@code ArrayList<String>} containing the names of the selected nutrients; may be empty
     * but not {@code null}
     */
    public ArrayList<String> getSelectedNutrients() {
        return selectedNutrients;
    }
}
