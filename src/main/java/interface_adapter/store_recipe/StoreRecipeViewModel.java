package interface_adapter.store_recipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StoreRecipeViewModel extends ViewModel {
    private final String name = "Store Recipe View";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private StoreRecipeState state = new StoreRecipeState();

    public StoreRecipeViewModel() {
        super("StoreRecipe");
    }

    public void setState(StoreRecipeState state) {
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

    public StoreRecipeState getState() {
        return state;
    }
}
