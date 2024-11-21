package app;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypePresenter;
import interface_adapter.DishType.DishTypeViewModel;
import data_access.FileUserDataAccessObject;
import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesPresenter;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepagePresenter;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupController;
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
import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInteractor;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import view.GetCaloriesView;
import view.HomepageView;
import view.StoreRecipeView;
import view.DishTypeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("data.json");

    private HomepageView homepageView;
    private HomepageViewModel homepageViewModel;

    private StoreRecipeView storeRecipeView;
    private StoreRecipeViewModel storeRecipeViewModel;

    private DishTypeView dishTypeView;
    private DishTypeViewModel dishTypeViewModel;
    private DishTypeUserDataAccessInterface dishTypeDAO;

    private GetCaloriesView getCaloriesView;
    private GetCaloriesViewModel getCaloriesViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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

    public AppBuilder addHomepageUseCase() {
        final HomepageOutputBoundary homepageOutputBoundary = new HomepagePresenter(viewManagerModel,
                homepageViewModel, storeRecipeViewModel);
        final HomepageInputBoundary userStoreRecipeInteractor = new HomepageInteractor(userDataAccessObject, homepageOutputBoundary);

        final HomepageController controller = new HomepageController(userStoreRecipeInteractor);
        homepageView.setHomepageController(controller);
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
        cardPanel.add(getCaloriesView, getCaloriesView.getName());
        return this;
    }

    public AppBuilder addStoreRecipeUseCase() {
        final StoreRecipeOutputBoundary storeRecipeOutputBoundary = new StoreRecipePresenter(viewManagerModel, storeRecipeViewModel);
        final StoreRecipeInputBoundary userStoreRecipeInteractor = new StoreRecipeInteractor(userDataAccessObject, storeRecipeOutputBoundary);

        final StoreRecipeController controller = new StoreRecipeController(userStoreRecipeInteractor);
        storeRecipeView.setStoreRecipeController(controller);
        return this;
    }

    public AppBuilder addDishTypeView() {
        dishTypeViewModel = new DishTypeViewModel();
        dishTypeView = new DishTypeView(dishTypeViewModel);
        cardPanel.add(dishTypeView, dishTypeView.getName());
        return this;
    }

    public AppBuilder addDishTypeUseCase() {
        final DishTypeOutputBoundary dishTypeOutputBoundary = new DishTypePresenter(dishTypeViewModel, viewManagerModel);

        final DishTypeInputBoundary dishTypeInteractor = new DishTypeInteractor(dishTypeDAO, dishTypeOutputBoundary);

        final DishTypeController controller = new DishTypeController(dishTypeInteractor);
        dishTypeView.setDishTypeController(controller);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homepageView.getName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

    public JFrame search() {
        final JFrame application = new JFrame("Dish Type Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(dishTypeView.getName());
        viewManagerModel.firePropertyChanged();

        return application;
    }


}
