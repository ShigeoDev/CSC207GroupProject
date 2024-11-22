package interface_adapter.DishType;

import interface_adapter.ViewModel;

public class DishTypeViewModel extends ViewModel<DishTypeState>{
    public DishTypeViewModel(){
        super("Dish Type");
        setState(new DishTypeState());
    }
}
