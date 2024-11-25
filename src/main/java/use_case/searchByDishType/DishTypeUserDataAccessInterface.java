package use_case.searchByDishType;

import entity.User;

import java.util.List;

public interface DishTypeUserDataAccessInterface {
    List<String> getRecipeByDishType(String dishType);
}
