package use_case.NutritionFilterPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Filter Based on Nutrition Interactor.
 */
public class NutritionFilterPageInteractor implements NutritionFilterPageInputBoundrary {
    private final NutritionFilterPageDataAccessInterface dataAccess;
    private final NutritionFilterPageOutputBoundary outputBoundary;

    public NutritionFilterPageInteractor(NutritionFilterPageDataAccessInterface dataAccess,
                                         NutritionFilterPageOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the Nutrition Filter Page use case by processing the user's selected nutrients.
     * @param nutritionFilterPageInputData the input data containing the list of selected nutrients
     */
    @Override
    public void execute(NutritionFilterPageInputData nutritionFilterPageInputData) {
        try {
            List<String> selectedNutrients = nutritionFilterPageInputData.getSelectedNutrients();
            if (selectedNutrients == null || selectedNutrients.isEmpty()) {
                outputBoundary.prepareFailView("No nutrients selected for filtering.");
                return;
            }

            JSONArray recipesJsonArray = dataAccess.getRecipesByNutrients(selectedNutrients);

            if (recipesJsonArray.isEmpty()) {
                outputBoundary.prepareFailView("No recipes found matching the selected nutrients.");
            } else {
                // Parse JSONArray into a list of recipe names or details
                List<String> recipeNames = new ArrayList<>();
                for (int i = 0; i < recipesJsonArray.length(); i++) {
                    JSONObject recipeJson = recipesJsonArray.getJSONObject(i).getJSONObject("recipe");
                    String recipeName = recipeJson.getString("label");
                    recipeNames.add(recipeName);
                }
                NutritionFilterPageOutputData outputData = new NutritionFilterPageOutputData(recipeNames,
                        false);
                outputBoundary.prepareSuccessView(outputData);
            }
        } catch (RuntimeException e) {
            outputBoundary.prepareFailView("An error occurred while fetching recipes: " + e.getMessage());
        }
    }

    @Override
    public void switchToHomepage(String username) {
        outputBoundary.prepareHomepage(username);
    }

    /**
     * Switches the user interface to the NutritionFilter view.
     * This method delegates to the presenter to handle the UI transition.
     */
    @Override
    public void switchToNutritionFilterPage(String username) {
        outputBoundary.prepareNutritionFilterPage(username);
    }
}
