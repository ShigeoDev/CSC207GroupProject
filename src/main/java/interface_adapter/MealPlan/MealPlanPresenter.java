package interface_adapter.MealPlan;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONObject;
import use_case.MealPlan.MealPlanOutputBoundary;
import use_case.MealPlan.MealPlanOutputData;
import view.MealPlanView;

public class MealPlanPresenter implements MealPlanOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;
    private final MealPlanViewModel mealPlanViewModel;

    public MealPlanPresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel,
                             MealPlanViewModel mealPlanViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
    }

    @Override
    public void prepareSuccessView(MealPlanOutputData outputData) {
        final MealPlanState mealPlanState = mealPlanViewModel.getState();
        mealPlanState.setRecipes(outputData.getRecipes()) ;
        mealPlanState.setUser(outputData.getUsername());
        mealPlanViewModel.firePropertyChanged();

        viewManagerModel.setState(mealPlanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goHome() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
