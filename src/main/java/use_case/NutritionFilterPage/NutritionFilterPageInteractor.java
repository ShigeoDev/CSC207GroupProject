package use_case.NutritionFilterPage;

import entity.Nutrient;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Recipe> allRecipes = dataAccess.getAllRecipes();
        List<String> selectedNutrients = nutritionFilterPageInputData.getSelectedNutrients();

        List<String> matchedRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            Map<String, Double> nutrientsMap = recipe.getNutrients();
            boolean containsAllNutrients = true;

            for (String nutrientName : selectedNutrients) {
                if (!nutrientsMap.containsKey(nutrientName)) {
                    containsAllNutrients = false;
                    break;
                }
            }

            if (containsAllNutrients) {
                matchedRecipes.add(recipe.getName());
            }
        }

        NutritionFilterPageOutputData outputData = new NutritionFilterPageOutputData(matchedRecipes, false);
        outputBoundary.prepareSuccessView(outputData);
    }
}
