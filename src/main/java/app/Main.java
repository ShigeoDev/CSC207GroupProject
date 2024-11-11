package app;

import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import view.HomepageView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/*
Run this to run program
 */
public class Main {
    public static void main(String[] args) {
        final int width = 1000;
        final int height = 1000;
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Recipe Application");
        frame.setSize(width, height);

        final CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        frame.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        final StoreRecipeViewModel storeRecipeViewModel = new StoreRecipeViewModel();
        final HomepageViewModel homepageViewModel = new HomepageViewModel();

        HomepageView homepageView = HomepageUseCaseFactory.create(viewManagerModel, homepageViewModel);

        views.add(homepageView, homepageView.getName());

        viewManagerModel.setActiveView(homepageView.getName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }
}
