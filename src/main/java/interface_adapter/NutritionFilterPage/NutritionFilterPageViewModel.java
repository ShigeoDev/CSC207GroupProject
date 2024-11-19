package interface_adapter.NutritionFilterPage;

import interface_adapter.ViewModel;

/**
 * The View Model for the Filter Based on Nutrition View.
 */
public class NutritionFilterPageViewModel extends ViewModel<NutritionFilterPageState> {
    public NutritionFilterPageViewModel() {
        super("Nutrition Filter");
        setState(new NutritionFilterPageState());
    }
}
