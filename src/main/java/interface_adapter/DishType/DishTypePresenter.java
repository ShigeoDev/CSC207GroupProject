package interface_adapter.DishType;

import interface_adapter.ViewManagerModel;
import use_case.searchByDishType.DishTypeOutputBoundary;
import use_case.searchByDishType.DishTypeOutputData;

import java.util.List;

public class DishTypePresenter implements DishTypeOutputBoundary {

    private final DishTypeViewModel dishTypeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DishTypePresenter(DishTypeViewModel dishTypeViewModel, ViewManagerModel viewManagerModel) {
        this.dishTypeViewModel = dishTypeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DishTypeOutputData outputData) {
        List<String> recipe = outputData.getRecipe();
        System.out.println("Dish found: " + recipe);
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
