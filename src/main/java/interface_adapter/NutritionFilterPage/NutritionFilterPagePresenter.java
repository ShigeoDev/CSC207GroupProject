package interface_adapter.NutritionFilterPage;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.NutritionFilterPage.NutritionFilterPageOutputBoundary;
import use_case.NutritionFilterPage.NutritionFilterPageOutputData;

/**
 * The Presenter for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPagePresenter implements NutritionFilterPageOutputBoundary {

    private final NutritionFilterPageViewModel nutritionFilterPageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    public NutritionFilterPagePresenter(NutritionFilterPageViewModel nutritionFilterPageViewModel,
                                        ViewManagerModel viewManagerModel,
                                        HomepageViewModel homepageViewModel) {
        this.nutritionFilterPageViewModel = nutritionFilterPageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
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

    /**
     * Prepares the NutritionFilterPageView by firing a property change event in the ViewModel
     * and updating the state in the ViewManager.
     */
    @Override
    public void prepareNutritionFilterPage(String username) {
        NutritionFilterPageState state = new NutritionFilterPageState();
        state.setUsername(username);
        nutritionFilterPageViewModel.setState(state);
        nutritionFilterPageViewModel.firePropertyChanged();
        viewManagerModel.setState(nutritionFilterPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareHomepage(String username) {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
