package app;

import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepagePresenter;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import use_case.Homepage.HomepageInputBoundary;
import use_case.Homepage.HomepageInteractor;
import use_case.Homepage.HomepageOutputBoundary;
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

    // thought question: is the hard dependency below a problem?
    // private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

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
        //final HomepageOutputBoundary homepageOutputBoundary = new HomepagePresenter(viewManagerModel,
         //       homepageViewModel);
        //final HomepageInputBoundary userSignupInteractor = new HomepageInteractor(
          //      userDataAccessObject, signupOutputBoundary, userFactory);

        //final HomepageController controller = new HomepageController(userSignupInteractor);
        //homepageView.setSignupController(controller);
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

    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homepageView.getName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
