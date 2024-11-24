package interface_adapter.MealPlan;

import interface_adapter.ViewModel;
import interface_adapter.store_recipe.StoreRecipeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private MealPlanState state = new MealPlanState();

    public MealPlanViewModel() {
        super("MealPlan");
    }

    public void setState(MealPlanState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MealPlanState getState() {
        return state;
    }
}
