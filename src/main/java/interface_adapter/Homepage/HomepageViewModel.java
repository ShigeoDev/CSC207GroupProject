package interface_adapter.Homepage;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View mode for the Homepage view component.
 * Manages the state and UI data for the Homepage view.
 * Handles property changes.
 */
public class HomepageViewModel extends ViewModel {
    public final String Search_BUTTON_LABEL = "Search";
    public final String Saved_BUTTON_LABEL = "Saved";
    public final String MealPlan_BUTTON_LABEL = "Meal Planner";
    public final String GetCalories_BUTTON_LABEL = "Get Calories";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private HomepageState state = new HomepageState();

    /**
     * Constructor for a new Homepage View Model.
     * Initializes the view name as "Homepage".
     */
    public HomepageViewModel() {
        super("Homepage");
    }

    /**
     * Setter for the current state of the homepage.
     * @param state The new state to set
     */
    public void setState(HomepageState state) {
        this.state = state;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a listener to be notified of state changes.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter for the current state of the homepage.
     * @return The current Homepage state
     */
    public HomepageState getState() {
        return state;
    }
}
