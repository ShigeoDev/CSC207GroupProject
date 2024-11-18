package interface_adapter.NutritionFilterPage;

import interface_adapter.ViewManagerModel;
import use_case.NutritionFilterPage.NutritionFilterPageOutputBoundary;
import use_case.NutritionFilterPage.NutritionFilterPageOutputData;

public class NutritionFilterPagePresenter implements NutritionFilterPageOutputBoundary {

    private final NutritionFilterPageViewModel nutritionFilterPageViewModel;
    private final ViewManagerModel viewManagerModel;

    public NutritionFilterPagePresenter(ViewManagerModel viewManagerModel,
                                        NutritionFilterPageViewModel nutritionFilterPageViewModel) {
        this.nutritionFilterPageViewModel = nutritionFilterPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(NutritionFilterPageOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
