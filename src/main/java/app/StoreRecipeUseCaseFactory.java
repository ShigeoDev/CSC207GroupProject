package app;


import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import view.HomepageView;
import view.StoreRecipeView;

public class StoreRecipeUseCaseFactory {

    /** Prevent instantiation. */
    private StoreRecipeUseCaseFactory() {}

    public static StoreRecipeView create(ViewManagerModel viewManagerModel, StoreRecipeViewModel storeRecipeViewModel) {

            // final HomepageController homepageController = createUserSignupUseCase(viewManagerModel, HomepageViewModel);
            return new StoreRecipeView(storeRecipeViewModel);

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