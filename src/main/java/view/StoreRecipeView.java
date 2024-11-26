package view;

import interface_adapter.Homepage.HomepageController;
import interface_adapter.store_recipe.StoreRecipeController;
import interface_adapter.store_recipe.StoreRecipeState;
import interface_adapter.store_recipe.StoreRecipeViewModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class StoreRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "StoreRecipe";
    private final StoreRecipeViewModel storeRecipeViewModel;
    private StoreRecipeController storeRecipeController;
    private HomepageController homepageController;
    private final JButton homeButton = new JButton("Home");

    private final JPanel recipesPanel = new JPanel();

    /**
     * A window with a title and a JButton.
     */
    public StoreRecipeView(StoreRecipeViewModel storeRecipeViewModel) {
        this.storeRecipeViewModel = storeRecipeViewModel;
        this.storeRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Stored Recipes");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        recipesPanel.setLayout(new BoxLayout(recipesPanel, BoxLayout.Y_AXIS));

        homeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        homepageController.execute();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(recipesPanel);
        this.add(homeButton);
    }

    public String getName() {
        return this.name;
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        recipesPanel.removeAll();
        if (evt.getPropertyName().equals("state")) {
            final StoreRecipeState state = (StoreRecipeState) evt.getNewValue();
            final ArrayList<JSONObject> recipes = state.getRecipes();
            for (int i = 0; i < recipes.size(); i++) {
                final RecipePanel recipePanel = new RecipePanel(recipes.get(i));
                recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                recipesPanel.add(recipePanel);
            }
        }
    }

    public void setStoreRecipeController(StoreRecipeController controller) {
        this.storeRecipeController = controller;
    }
    public void setHomepageController(HomepageController controller) {
        this.homepageController = controller;
    }
}


