package use_case.searchByDishType;

import org.json.JSONArray;

public class DishTypeInteractor implements DishTypeInputBoundary {

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
            // Retrieve recipes based on dish type.
            JSONArray recipes = userDataAccessInterface.getRecipeByDishType(dishType);

            // Prepare and send the success view using the output presenter.
            DishTypeOutputData outputData = new DishTypeOutputData(recipes, false);
            userPresenter.prepareSuccessView(outputData);

            return recipes;
        } catch (Exception e) {
            // Prepare and send the failure view in case of an error.
            DishTypeOutputData errorData = new DishTypeOutputData(new JSONArray(), true);
            userPresenter.prepareFailView(errorData);
        }
        return null;
    }

    @Override
    public void switchToDishType(String username) {
        userPresenter.prepareDishType(username);
    }

    @Override
    public void switchToHomepage(String username) {
        userPresenter.prepareHomepage(username);
    }
}

