package interface_adapter.DishType;

import interface_adapter.ViewModel;

/**
 * The DishTypeViewModel class extends the ViewModel class and is responsible for managing the
 * state of the dish type view. It handles the dish type view's
 * state and triggers view updates based on changes in that state.
 */
public class DishTypeViewModel extends ViewModel<DishTypeState> {

    /**
     * Constructs a new DishTypeViewModel instance and initializes the state with a new
     * DishTypeState.
     */
    public DishTypeViewModel(){
        super("Dish Type");  // Sets the view's name as "Dish Type"
        setState(new DishTypeState());  // Initializes the state with a new DishTypeState
    }
}

