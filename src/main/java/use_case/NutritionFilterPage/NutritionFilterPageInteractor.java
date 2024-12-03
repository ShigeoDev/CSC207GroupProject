package use_case.NutritionFilterPage;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * The Filter Based on Nutrition Interactor.
 */
public class NutritionFilterPageInteractor implements NutritionFilterPageInputBoundrary {
    private final NutritionFilterPageDataAccessInterface dataAccess;
    private final NutritionFilterPageOutputBoundary outputBoundary;

    /**
     * Constructs a new {@code NutritionFilterPageInteractor} with the specified data access interface and output boundary.
     * <p>
     * This constructor initializes the interactor for the Nutrition Filter Page use case, setting up
     * the necessary components for accessing data and presenting output.
     * </p>
     *
     * @param dataAccess      the {@code NutritionFilterPageDataAccessInterface} used for data retrieval
     * @param outputBoundary  the {@code NutritionFilterPageOutputBoundary} used for presenting the results
     */
    public NutritionFilterPageInteractor(NutritionFilterPageDataAccessInterface dataAccess,
                                         NutritionFilterPageOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the Nutrition Filter Page use case by processing the user's selected nutrients.
     *
     * @param nutritionFilterPageInputData the input data containing the list of selected nutrients
     * @return a {@code JSONArray} of recipes matching the selected nutrients,
     * or {@code null} if an error occurs.
     */
    @Override
    public JSONArray execute(NutritionFilterPageInputData nutritionFilterPageInputData) {
        try {
            ArrayList<String> selectedNutrients = nutritionFilterPageInputData.getSelectedNutrients();
            if (selectedNutrients.isEmpty()) {
                outputBoundary.prepareFailView("No nutrients selected for filtering.");
                return null;
            }

            JSONArray recipes = dataAccess.getRecipesByNutrients(selectedNutrients);

            if (recipes.isEmpty()) {
                outputBoundary.prepareFailView("No recipes found matching the selected nutrients.");
                return null;
            } else {
                NutritionFilterPageOutputData outputData = new NutritionFilterPageOutputData(recipes);
                outputBoundary.prepareSuccessView(outputData);
                return recipes;
            }
        } catch (RuntimeException e) {
            outputBoundary.prepareFailView("An error occurred while fetching recipes: " + e.getMessage());
            return null;
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
