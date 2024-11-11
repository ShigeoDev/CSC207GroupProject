package app;


import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import view.HomepageView;

import javax.swing.*;
import java.io.IOException;

public class HomepageUseCaseFactory {

    /** Prevent instantiation. */
    private HomepageUseCaseFactory() {}

    public static HomepageView create(ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {

            // final HomepageController homepageController = createUserSignupUseCase(viewManagerModel, HomepageViewModel);
            return new HomepageView(homepageViewModel);

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