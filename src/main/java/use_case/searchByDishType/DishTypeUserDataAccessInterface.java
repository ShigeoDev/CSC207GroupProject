package use_case.searchByDishType;

import entity.User;
import org.json.JSONArray;

import java.util.List;

public interface DishTypeUserDataAccessInterface {
    JSONArray getRecipeByDishType(String dishType);
}
