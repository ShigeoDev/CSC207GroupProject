package app;


import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.ViewManagerModel;
import view.GetCaloriesView;
import view.HomepageView;

import javax.swing.*;
import java.io.IOException;

public class GetCaloriesUseCaseFactory {

    /** Prevent instantiation. */
    private GetCaloriesUseCaseFactory() {}

    public static GetCaloriesView create(ViewManagerModel viewManagerModel, GetCaloriesViewModel getCaloriesViewModel) {

        // final HomepageController homepageController = createUserSignupUseCase(viewManagerModel, HomepageViewModel);
        return new GetCaloriesView(getCaloriesViewModel);

    }

//    private static HomepageController createUserSignupUseCase(ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) throws IOException {
//        UserSignupDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
//
//        // Notice how we pass this method's parameters to the Presenter.
//        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
//
//        UserFactory userFactory = new CommonUserFactory();
//
//        SignupInputBoundary userSignupInteractor = new SignupInteractor(
//                userDataAccessObject, signupOutputBoundary, userFactory);
//
//        return new SignupController(userSignupInteractor);
//    }
}