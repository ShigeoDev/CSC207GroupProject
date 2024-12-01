package interface_adapter.MealPlan;

import interface_adapter.ViewModel;
import interface_adapter.store_recipe.StoreRecipeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the meal plan, managing its state and tell listeners of changes.
 */
public class MealPlanViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this); // Handles property change notifications.

    private MealPlanState state = new MealPlanState(); // Current state of the meal plan.

    /**
     * Constructor initializing the ViewModel with the name "MealPlan".
     */
    public MealPlanViewModel() {
        super("MealPlan");
    }

    /**
     * Updates the meal plan state.
     * @param state The new meal plan state.
     */
    public void setState(MealPlanState state) {
        this.state = state;
    }

    /**
     * Notifies listeners about a change in the meal plan state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to observe state changes.
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current meal plan state.
     * @return The meal plan state.
     */
    public MealPlanState getState() {
        return state;
    }
}
