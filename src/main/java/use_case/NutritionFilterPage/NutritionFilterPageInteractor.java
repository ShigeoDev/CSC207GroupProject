package use_case.NutritionFilterPage;

import entity.Recipe;

import java.util.List;

/**
 * The Filter Based on Nutrition Interactor.
 */
public class NutritionFilterPageInteractor implements NutritionFilterPageInputBoundrary {
    private final NutritionFilterPageDataAccessInterface nutritionFilterPageDataAccessInterface;
    private final NutritionFilterPageOutputBoundary nutritionFilterPageOutputBoundary;

    public NutritionFilterPageInteractor(NutritionFilterPageDataAccessInterface nutritionFilterPageDataAccessInterface,
                                         NutritionFilterPageOutputBoundary nutritionFilterPageOutputBoundary) {
        this.nutritionFilterPageDataAccessInterface = nutritionFilterPageDataAccessInterface;
        this.nutritionFilterPageOutputBoundary = nutritionFilterPageOutputBoundary;
    }

    @Override
    public void execute(NutritionFilterPageInputData nutritionFilterPageInputData) {
        List<Recipe> filteredRecipes = nutritionFilterPageDataAccessInterface.filterRecipes(
                nutritionFilterPageInputData);
        NutritionFilterPageOutputData outputData = new NutritionFilterPageOutputData(filteredRecipes,
                false);
        nutritionFilterPageOutputBoundary.prepareSuccessView(outputData);
    }
}
