package interface_adapter.DishType;

import org.json.JSONArray;
import use_case.searchByDishType.DishTypeInputBoundary;
import use_case.searchByDishType.DishTypeInputData;

public class DishTypeController {
    private final DishTypeInputBoundary dishTypeInteractor;

    public DishTypeController(DishTypeInputBoundary dishTypeInteractor) {
        this.dishTypeInteractor = dishTypeInteractor;
    }

    public JSONArray execute(String dishType) {
        DishTypeInputData inputData = new DishTypeInputData(dishType);
        return dishTypeInteractor.execute(inputData);
    }
}
