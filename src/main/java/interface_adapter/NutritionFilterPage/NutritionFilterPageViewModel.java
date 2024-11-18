package interface_adapter.NutritionFilterPage;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NutritionFilterPageViewModel extends ViewModel {
    public final String name = "NutritionFilterPage View";

    public final String Search_BUTTON_LABEL = "Saved";
    public final String Saved_BUTTON_LABEL = "Search";
    public final String Filter_BUTTON_LABEL = "Filter Based on Nutrition";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private NutritionFilterPageState state = new NutritionFilterPageState();

    public NutritionFilterPageViewModel() {
        super("NutritionFilterPage");
    }

    public void setState(NutritionFilterPageState state) {
        this.state = state;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public NutritionFilterPageState getState() {
        return state;
    }

}
