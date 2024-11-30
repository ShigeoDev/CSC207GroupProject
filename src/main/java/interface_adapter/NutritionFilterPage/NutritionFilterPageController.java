package interface_adapter.NutritionFilterPage;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.NutritionFilterPage.NutritionFilterPageInputBoundrary;
import use_case.NutritionFilterPage.NutritionFilterPageInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * The Controller for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageController {
    private final NutritionFilterPageInputBoundrary interactor;

    /**
     * Constructs a new {@code NutritionFilterPageController} with the specified interactor.
     * <p>
     * This constructor initializes the controller for the Nutrition Filter Page use case,
     * setting up the interaction between the user's actions and the use case interactor.
     * </p>
     *
     * @param interactor the {@code NutritionFilterPageInputBoundrary} that handles the input processing
     *                   and use case execution for filtering based on nutrition
     */
    public NutritionFilterPageController(NutritionFilterPageInputBoundrary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the Filter Based on Nutrition Use Case.
     * @param selectedNutrients the selected nutrients
     * @return @return a {@code JSONArray} of recipes matching the selected nutrients.
     */
    public JSONArray execute(ArrayList<String> selectedNutrients) {
        if (selectedNutrients == null || selectedNutrients.isEmpty()) {
            throw new IllegalArgumentException("Selected nutrients cannot be null or empty.");
        }

        final NutritionFilterPageInputData nutritionFilterPageInputData = new NutritionFilterPageInputData(
                selectedNutrients);

        return interactor.execute(nutritionFilterPageInputData);
    }

    /**
     * Switches to the NutritionFilterPage.
     */
    public void switchToNutritionFilterPage(String username) {
        interactor.switchToNutritionFilterPage(username);
    }

    /**
     * Switches to the Homepage.
     */
    public void switchToHomepage(String username) {
        interactor.switchToHomepage(username);
    }
}
