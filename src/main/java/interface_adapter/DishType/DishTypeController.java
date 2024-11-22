package interface_adapter.DishType;

import use_case.searchByDishType.DishTypeInputBoundary;
import use_case.searchByDishType.DishTypeInputData;

public class DishTypeController {
    private final DishTypeInputBoundary dishTypeInteractor;

    public DishTypeController(DishTypeInputBoundary dishTypeInteractor) {
        this.dishTypeInteractor = dishTypeInteractor;
    }

    public void execute(String dishType) {
        DishTypeInputData inputData = new DishTypeInputData(dishType);
        dishTypeInteractor.execute(inputData);
    }
}
