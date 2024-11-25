package interface_adapter.NutritionFilterPage;

import use_case.NutritionFilterPage.NutritionFilterPageOutputBoundary;
import use_case.NutritionFilterPage.NutritionFilterPageOutputData;

/**
 * The Presenter for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPagePresenter implements NutritionFilterPageOutputBoundary {

    private final NutritionFilterPageViewModel nutritionFilterPageViewModel;

    public NutritionFilterPagePresenter(NutritionFilterPageViewModel nutritionFilterPageViewModel) {
        this.nutritionFilterPageViewModel = nutritionFilterPageViewModel;
    }

    @Override
    public void prepareSuccessView(NutritionFilterPageOutputData outputData) {
        NutritionFilterPageState state = nutritionFilterPageViewModel.getState();

        state.setRecipeDetails(outputData.getRecipes());
        state.setSearchError(null);

        nutritionFilterPageViewModel.setState(state);
    }

    @Override
    public void prepareFailView(String error) {
        NutritionFilterPageState state = nutritionFilterPageViewModel.getState();

        state.setSearchError(error);
        state.setRecipeDetails(null);

        nutritionFilterPageViewModel.setState(state);
    }
}
