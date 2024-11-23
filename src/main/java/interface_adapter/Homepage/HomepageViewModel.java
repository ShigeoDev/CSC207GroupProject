package interface_adapter.Homepage;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomepageViewModel extends ViewModel {
    public final String Search_BUTTON_LABEL = "Search";
    public final String Saved_BUTTON_LABEL = "Saved";
    public final String MealPlan_BUTTON_LABEL = "Meal Planner";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private HomepageState state = new HomepageState();

    public HomepageViewModel() {
        super("Homepage");
    }

    public void setState(HomepageState state) {
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

    public HomepageState getState() {
        return state;
    }
}
