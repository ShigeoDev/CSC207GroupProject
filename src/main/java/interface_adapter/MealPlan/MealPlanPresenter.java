package interface_adapter.MealPlan;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONObject;
import use_case.MealPlan.MealPlanOutputBoundary;

public class MealPlanPresenter implements MealPlanOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    public MealPlanPresenter(ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void goHome() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
