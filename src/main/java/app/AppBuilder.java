package app;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypePresenter;
import interface_adapter.DishType.DishTypeViewModel;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepagePresenter;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupController;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.searchByDishType.DishTypeInputBoundary;
import use_case.searchByDishType.DishTypeInteractor;
import use_case.searchByDishType.DishTypeOutputBoundary;
import use_case.searchByDishType.DishTypeUserDataAccessInterface;
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


    // thought question: is the hard dependency below a problem?
    // private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private HomepageView homepageView;
    private HomepageViewModel homepageViewModel;

    private StoreRecipeView storeRecipeView;
    private StoreRecipeViewModel storeRecipeViewModel;

    private DishTypeView dishTypeView;
    private DishTypeViewModel dishTypeViewModel;
    private DishTypeUserDataAccessInterface dishTypeDAO;

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
        final HomepageInputBoundary userStoreRecipeInteractor = new HomepageInteractor(homepageOutputBoundary);

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

    public AppBuilder addStoreRecipeUseCase() {
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
