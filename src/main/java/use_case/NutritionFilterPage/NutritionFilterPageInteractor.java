package use_case.NutritionFilterPage;

import entity.Recipe;

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

    @Override
    public void execute(NutritionFilterPageInputData nutritionFilterPageInputData) {
        List<String> selectedNutrients = nutritionFilterPageInputData.getSelectedNutrients();
        if (selectedNutrients == null || selectedNutrients.isEmpty()) {
            outputBoundary.prepareFailView("No nutrients selected for filtering.");
            return;
        }

        List<Recipe> recipes = dataAccess.getRecipesByNutrients(selectedNutrients);

        if (recipes.isEmpty()) {
            outputBoundary.prepareFailView("No recipes found matching the selected nutrients.");
        } else {
            NutritionFilterPageOutputData outputData = new NutritionFilterPageOutputData(recipes, false);
            outputBoundary.prepareSuccessView(outputData);
        }
    }
}
