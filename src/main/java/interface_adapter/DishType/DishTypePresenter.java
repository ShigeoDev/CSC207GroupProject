package interface_adapter.DishType;

import use_case.searchByDishType.DishTypeOutputBoundary;
import use_case.searchByDishType.DishTypeOutputData;

public class DishTypePresenter implements DishTypeOutputBoundary {

    @Override
    public void prepareSuccessView(DishTypeOutputData outputData) {
        String recipe = outputData.getRecipe();
        System.out.println("Dish found: " + recipe);
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
