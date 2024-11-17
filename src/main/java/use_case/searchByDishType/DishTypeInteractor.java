package use_case.searchByDishType;

import entity.User;

public class DishTypeInteractor implements DishTypeInputBoundary{
    private final DishTypeUserDataAccessInterface userDataAccessInterface;
    private final DishTypeOutputBoundary userPresenter;

    public DishTypeInteractor(DishTypeUserDataAccessInterface userDataAccessInterface, DishTypeOutputBoundary userPresenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(DishTypeInputData dishTypeInputData) {
        String dishType = dishTypeInputData.getDishType();
        final DishTypeOutputData outputData = new DishTypeOutputData(userDataAccessInterface.searchDishType(dishType),
                false
        );
        userPresenter.prepareSuccessView(outputData);
    }
}
