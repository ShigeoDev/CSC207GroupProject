package interface_adapter.GetCalories;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetCaloriesViewModel extends ViewModel {

    public static final String RECIPE_LABEL = "Recipe Name";
    public final String Submit_BUTTON_LABEL = "Submit";

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private GetCaloriesState state = new GetCaloriesState();

    public GetCaloriesViewModel() {
        super("CaloriesView");
    }

    public void setState(GetCaloriesState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GetCaloriesState getState() {
        return state;
    }

    public String getUsername() {
        return state.getUsername();
    }
}
