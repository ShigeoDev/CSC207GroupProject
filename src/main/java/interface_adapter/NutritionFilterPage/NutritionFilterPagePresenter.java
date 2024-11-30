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

    /**
     * Constructs a new {@code NutritionFilterPagePresenter} with the specified view models.
     * <p>
     * This constructor initializes the presenter for the Nutrition Filter Page use case.
     * It sets up the necessary view models to handle the interactions between the Nutrition Filter Page,
     * the View Manager, and the Homepage.
     * </p>
     *
     * @param nutritionFilterPageViewModel the {@code NutritionFilterPageViewModel} used to manage the state of the Nutrition Filter Page view
     * @param viewManagerModel             the {@code ViewManagerModel} used to manage and coordinate the overall view state transitions
     * @param homepageViewModel            the {@code HomepageViewModel} used to manage the state of the Homepage view
     */
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

        // Ensure the state is not null before modifying it.
        if (state == null) {
            state = new NutritionFilterPageState();
        }

        // Set the new data.
        state.setRecipeDetails(outputData.getRecipes());
        state.setSearchError(null);

        // Update the state in the ViewModel.
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

    /**
     * Prepares the HomepageView by firing a property change event in the ViewModel
     * and updating the state in the ViewManager.
     */
    @Override
    public void prepareHomepage(String username) {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
