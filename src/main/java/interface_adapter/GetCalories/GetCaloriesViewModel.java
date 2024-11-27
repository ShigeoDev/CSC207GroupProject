package interface_adapter.GetCalories;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A view model class that handles the presentation logic for the Get Calories use case.
 * Extends ViewModel and manages the state and propery change notifications
 * regarding the Get Calorie use case.
 */
public class GetCaloriesViewModel extends ViewModel {

    /**
     * Label for the recipe input field.
     */
    public static final String RECIPE_LABEL = "Recipe Name";

    /**
     * Label for the submit button.
     */
    public final String Submit_BUTTON_LABEL = "Submit";

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private GetCaloriesState state = new GetCaloriesState();

    /**
     * Constructs a new GetCaloriesViewModel.
     * Initializes the view with the name "CaloriesView"
     */
    public GetCaloriesViewModel() {
        super("CaloriesView");
    }

    /**
     * Sets the current state of the view model.
     * @param state The new state to set
     */
    public void setState(GetCaloriesState state) {
        this.state = state;
    }

    /**
     * Notifies listeners of a change in the view model's state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this GetCalories view model.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the view model.
     * @return The current state
     */
    public GetCaloriesState getState() {
        return state;
    }

    /**
     * Gets the current username from current state.
     * @return The current username
     */
    public String getUsername() {
        return state.getUsername();
    }
}
