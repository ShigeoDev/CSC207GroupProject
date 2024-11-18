package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepagePresenter;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeController;
import interface_adapter.store_recipe.StoreRecipePresenter;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;
import use_case.store_recipe.StoreRecipeInputBoundary;
import use_case.store_recipe.StoreRecipeInteractor;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import view.HomepageView;
import view.StoreRecipeView;
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
        final HomepageInputBoundary userHomepageInteractor = new HomepageInteractor(userDataAccessObject, homepageOutputBoundary);

        final HomepageController controller = new HomepageController(userHomepageInteractor);
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
        final StoreRecipeOutputBoundary storeRecipeOutputBoundary = new StoreRecipePresenter(viewManagerModel, storeRecipeViewModel);
        final StoreRecipeInputBoundary userStoreRecipeInteractor = new StoreRecipeInteractor(userDataAccessObject, storeRecipeOutputBoundary);

        final StoreRecipeController controller = new StoreRecipeController(userStoreRecipeInteractor);
        storeRecipeView.setStoreRecipeController(controller);
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
}
