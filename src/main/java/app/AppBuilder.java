package app;

import data_access.ApiDataAccessObject;
import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypePresenter;
import interface_adapter.DishType.DishTypeViewModel;
import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.MealPlan.MealPlanController;
import interface_adapter.MealPlan.MealPlanPresenter;
import interface_adapter.MealPlan.MealPlanViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import use_case.MealPlan.MealPlanInputBoundary;
import use_case.MealPlan.MealPlanInteractor;
import use_case.MealPlan.MealPlanOutputBoundary;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesPresenter;
import interface_adapter.GetCalories.GetCaloriesViewModel;

import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepagePresenter;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeController;
import interface_adapter.store_recipe.StoreRecipePresenter;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import use_case.GetCalories.GetCaloriesInputBoundary;
import use_case.GetCalories.GetCaloriesInteractor;
import use_case.GetCalories.GetCaloriesOutputBoundary;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.searchByDishType.DishTypeInputBoundary;
import use_case.searchByDishType.DishTypeInteractor;
import use_case.searchByDishType.DishTypeOutputBoundary;
import use_case.searchByDishType.DishTypeUserDataAccessInterface;
import view.*;
import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInteractor;
import use_case.store_recipe.StoreRecipeOutputBoundary;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    // Panel to hold and switch between different views
    private final JPanel cardPanel = new JPanel();
    // CardLayout to manage the different views (panels) in the application
    private final CardLayout cardLayout = new CardLayout();
    // Factory for creating User objects
    private final UserFactory userFactory = new UserFactory();
    // Model to manage view states
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    // Manager to control switching between views
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Data access object for storing user data
    private final FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("data.json", userFactory);
    // Data access object for API
    private final ApiDataAccessObject apiDataAccessObject = new ApiDataAccessObject();

    // Various views and their corresponding view models
    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private LoginViewModel loginViewModel;
    private LoginView loginView;

    private HomepageView homepageView;
    private HomepageViewModel homepageViewModel;

    private StoreRecipeView storeRecipeView;
    private StoreRecipeViewModel storeRecipeViewModel;

    private DishTypeView dishTypeView;
    private DishTypeViewModel dishTypeViewModel;

    private GetCaloriesView getCaloriesView;
    private GetCaloriesViewModel getCaloriesViewModel;

    private ReturnCaloriesView returnCaloriesView;

    private MealPlanViewModel mealPlanViewModel;
    private MealPlanView mealPlanView;

    /**
     * Constructor for AppBuilder.
     * Initializes the cardPanel with a CardLayout.
     */
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Homepage View to the application.
     * @return this builder
     */
    public AppBuilder addHomepageView() {
        homepageViewModel = new HomepageViewModel();
        homepageView = new HomepageView(homepageViewModel);
        cardPanel.add(homepageView, homepageView.getName());
        return this;
    }

    /**
     * Adds the MealPlan View to the application.
     * Creates and initializes the mealPlanView and  mealPlanViewModel.
     * Add the mealPlanView with the cardPanel.
     *
     * @return this builder
     */
    public AppBuilder addMealPlanView() {
        mealPlanViewModel = new MealPlanViewModel();
        mealPlanView = new MealPlanView(mealPlanViewModel);
        cardPanel.add(mealPlanView, mealPlanView.getName());
        return this;
    }

    /**
     * Sets up the MealPlan use case.
     * Creates and connects the Presenter, Interactor, and Controller for MealPlan functionality.
     *
     * @return this builder
     */
    public AppBuilder addMealPlanUseCase() {
        final MealPlanOutputBoundary mealPlanOutputBoundary = new MealPlanPresenter(viewManagerModel,
                homepageViewModel,
                mealPlanViewModel);
        final MealPlanInputBoundary userMealPlanInteractor = new MealPlanInteractor(mealPlanOutputBoundary, apiDataAccessObject);

        final MealPlanController controller = new MealPlanController(userMealPlanInteractor);
        homepageView.setMealPlanController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                homepageViewModel, signupViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addHomepageUseCase() {
        final HomepageOutputBoundary homepageOutputBoundary = new HomepagePresenter(viewManagerModel,
                homepageViewModel, storeRecipeViewModel, mealPlanViewModel, dishTypeViewModel, getCaloriesViewModel);
        final HomepageInputBoundary userStoreRecipeInteractor = new HomepageInteractor(homepageOutputBoundary);

        final HomepageController controller = new HomepageController(userStoreRecipeInteractor);
        homepageView.setHomepageController(controller);
        mealPlanView.setHomepageController(controller);
        storeRecipeView.setHomepageController(controller);
        getCaloriesView.setHomepageController(controller);
        returnCaloriesView.setHomepageController(controller);
        return this;
    }

    public AppBuilder addStoreRecipeView() {
        storeRecipeViewModel = new StoreRecipeViewModel();
        storeRecipeView = new StoreRecipeView(storeRecipeViewModel);
        cardPanel.add(storeRecipeView, storeRecipeView.getName());
        return this;
    }

    public AppBuilder addGetCaloriesView() {
        getCaloriesViewModel = new GetCaloriesViewModel();
        getCaloriesView = new GetCaloriesView(getCaloriesViewModel);
        returnCaloriesView = new ReturnCaloriesView(getCaloriesViewModel);

        cardPanel.add(getCaloriesView, getCaloriesView.getName());
        cardPanel.add(returnCaloriesView, "Calorie Result");
        return this;
    }

    public AppBuilder addGetCaloriesUseCase() {
        final GetCaloriesOutputBoundary getCaloriesOutputBoundary = new GetCaloriesPresenter(
                viewManagerModel,
                getCaloriesViewModel);

        final GetCaloriesInputBoundary getCaloriesInteractor = new GetCaloriesInteractor(
                apiDataAccessObject,
                getCaloriesOutputBoundary,
                userDataAccessObject);

        final GetCaloriesController controller = new GetCaloriesController(getCaloriesInteractor);
        getCaloriesView.setGetCaloriesController(controller);
        returnCaloriesView.setController(controller);
        homepageView.setGetCaloriesController(controller);
        return this;
    }

    public AppBuilder addStoreRecipeUseCase() {
        final StoreRecipeOutputBoundary storeRecipeOutputBoundary = new StoreRecipePresenter(viewManagerModel, storeRecipeViewModel, homepageViewModel);
        final StoreRecipeInputBoundary userStoreRecipeInteractor = new StoreRecipeInteractor(userDataAccessObject, storeRecipeOutputBoundary);

        final StoreRecipeController controller = new StoreRecipeController(userStoreRecipeInteractor);
        homepageView.setStoreRecipeController(controller);
        mealPlanView.setStoreRecipeController(controller);
        returnCaloriesView.setStoreRecipeController(controller);
        dishTypeView.setStoreRecipeController(controller);
        return this;
    }

    /**
     * Adds a DishType view to the application.
     * This method creates a new instance of the DishTypeViewModel and DishTypeView,
     * and then adds the DishTypeView to the card panel of the application.
     *
     * @return the AppBuilder.
     */
    public AppBuilder addDishTypeView() {
        // Creates a new DishTypeViewModel instance
        dishTypeViewModel = new DishTypeViewModel();
        // Creates a new DishTypeView with the view model
        dishTypeView = new DishTypeView(dishTypeViewModel);
        // Adds the view to the card panel
        cardPanel.add(dishTypeView, dishTypeView.getName());
        // Returns the current instance
        return this;
    }

    /**
     * Adds the DishType use case to the application.
     * This method sets up the necessary components for the DishType use case, including:
     * - Creating the DishTypePresenter (OutputBoundary).
     * - Creating the DishTypeInteractor (InputBoundary).
     * - Creating and setting the DishTypeController.
     * The controller is then assigned to both the homepage and dish type views.
     *
     * @return the current instance of the AppBuilder to allow method chaining
     */
    public AppBuilder addDishTypeUseCase() {
        final DishTypeOutputBoundary dishTypeOutputBoundary = new DishTypePresenter(dishTypeViewModel, viewManagerModel, homepageViewModel);
        final DishTypeInputBoundary dishTypeInteractor = new DishTypeInteractor(apiDataAccessObject, dishTypeOutputBoundary);
        final DishTypeController controller = new DishTypeController(dishTypeInteractor);

        // Sets the controller for the homepage view
        homepageView.setDishTypeController(controller);
        // Sets the controller for the dish type view
        dishTypeView.setDishTypeController(controller);
        // Returns the current instance
        return this;
    }
    /**
     * Builds and returns the main application frame.
     * Initializes the application frame, sets its default state, and prepares the cardPanel.
     *
     * @return the JFrame containing the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Recipe Search");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
