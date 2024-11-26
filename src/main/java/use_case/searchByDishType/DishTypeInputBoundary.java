package use_case.searchByDishType;

import org.json.JSONArray;

public interface DishTypeInputBoundary {

    JSONArray execute(DishTypeInputData dishTypeInputData);
}
