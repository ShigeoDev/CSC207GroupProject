package use_case.searchByDishType;

import org.json.JSONArray;

public class DishTypeInteractor implements DishTypeInputBoundary{
    private final DishTypeUserDataAccessInterface userDataAccessInterface;
    private final DishTypeOutputBoundary userPresenter;

    public DishTypeInteractor(DishTypeUserDataAccessInterface userDataAccessInterface, DishTypeOutputBoundary userPresenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public JSONArray execute(DishTypeInputData dishTypeInputData) {
        String dishType = dishTypeInputData.getDishType();
        try {
            JSONArray recipes = userDataAccessInterface.getRecipeByDishType(dishType);
            DishTypeOutputData outputData = new DishTypeOutputData(recipes, false);
            userPresenter.prepareSuccessView(outputData);
            return userDataAccessInterface.getRecipeByDishType(dishType);
        } catch (Exception e) {
            DishTypeOutputData errorData = new DishTypeOutputData(new JSONArray(), true);
            userPresenter.prepareFailView(errorData);
        }
        return null;
    }
}
