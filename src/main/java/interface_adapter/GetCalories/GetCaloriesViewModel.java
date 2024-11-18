package interface_adapter.GetCalories;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetCaloriesViewModel extends ViewModel {

    public final String name = "Get Calories View";

    public static final String RECIPE_LABEL = "Recipe Name";
    public final String Submit_BUTTON_LABEL = "Submit";

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private GetCaloriesState state = new GetCaloriesState();

    public GetCaloriesViewModel() {
        super("GetCalories View");
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
}
