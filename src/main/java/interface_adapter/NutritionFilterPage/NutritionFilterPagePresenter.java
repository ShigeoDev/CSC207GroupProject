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

    /**
     * Updates the view model with the recipe details upon a successful nutrient filter operation.
     * @param outputData the output data containing the list of recipe names resulting from the nutrient filter
     */
    @Override
    public void prepareSuccessView(NutritionFilterPageOutputData outputData) {
        NutritionFilterPageState state = nutritionFilterPageViewModel.getState();

        state.setRecipeDetails(outputData.getRecipeNames());
        state.setSearchError(null);

        nutritionFilterPageViewModel.setState(state);
    }

    /**
     * Updates the view model with an error message upon a failed nutrient filter operation.
     * @param error a {@code String} containing the error message describing the failure reason
     */
    @Override
    public void prepareFailView(String error) {
        NutritionFilterPageState state = nutritionFilterPageViewModel.getState();

        state.setSearchError(error);
        state.setRecipeDetails(null);

        nutritionFilterPageViewModel.setState(state);
    }
}
