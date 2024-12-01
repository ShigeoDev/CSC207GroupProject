package view;

import interface_adapter.Homepage.HomepageController;
import interface_adapter.MealPlan.MealPlanController;
import interface_adapter.MealPlan.MealPlanState;
import interface_adapter.MealPlan.MealPlanViewModel;
import interface_adapter.store_recipe.StoreRecipeController;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * MealPlanView class represents the meal plan view, displaying recipes and user.
 */
public class MealPlanView extends JPanel implements PropertyChangeListener, ActionListener {

    // The view model representing the state of the meal plan.
    private final MealPlanViewModel mealPlanViewModel;
    // Controller for storing recipes.
    private StoreRecipeController storeRecipeController;
    // Controller for navigating to the homepage.
    private HomepageController homepageController;

    // Name of the view.
    private String viewName = "MealPlan";
    // Title label.
    private JLabel title = new JLabel("Meal Plan");
    // Panel for displaying recipes.
    private JPanel recipesPanel = new JPanel();
    // Scrollable panel for recipes.
    private JScrollPane scrollPane = new JScrollPane(recipesPanel);
    // Button to go to the homepage.
    private JButton home = new JButton("Home");

    /**
     * Constructor initializing the MealPlanView with the provided view model.
     * Sets up the layout and components.
     * @param mealPlanViewModel The view model for the meal plan.
     */
    public MealPlanView(MealPlanViewModel mealPlanViewModel) {
        this.mealPlanViewModel = mealPlanViewModel;
        // Registering the view as a listener for property changes.
        this.mealPlanViewModel.addPropertyChangeListener(this);

        // Setting up the layout for the panel and components.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        recipesPanel.setLayout(new BoxLayout(recipesPanel, BoxLayout.Y_AXIS));

        // Center-align the title.
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Center-align the home button.
        home.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding action listener for the "Home" button to navigate to the homepage.
        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        homepageController.execute();
                    }
                }
        );

        // Adding components to the panel.
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(20));
        this.add(home);
    }

    /**
     * Sets the controller for navigating to the homepage.
     * @param homepageController The homepage controller.
     */
    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    /**
     * Sets the controller for storing recipes.
     * @param storeRecipeController The store recipe controller.
     */
    public void setStoreRecipeController(StoreRecipeController storeRecipeController) {
        this.storeRecipeController = storeRecipeController;
    }

    /**
     * Handles action events. Currently just prints the action command.
     * @param evt The action event.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property changes from the view model, updating the recipes display.
     * @param evt The property change event.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            recipesPanel.removeAll(); // Clear the existing recipe panels.

            // Get the updated meal plan state and recipes.
            final MealPlanState state = (MealPlanState) evt.getNewValue();
            final JSONObject[] recipes = state.getRecipes();
            final String[] mealnames = {"Breakfast: ", "Lunch: ", "Dinner: "};

            // Add a recipe panel for each recipe.
            for (int i = 0; i < recipes.length; i++) {
                final JSONObject recipe = recipes[i];
                final RecipeSavePanel recipePanel = new RecipeSavePanel(recipe);

                // Add action listener for the "Save" button in each recipe panel.
                recipePanel.getSaveButton().addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Store the recipe when the save button is clicked.
                                storeRecipeController.execute(recipe, mealPlanViewModel.getState().getUser());
                            }
                        }
                );
                // Add the recipe panel to the panel.
                recipesPanel.add(recipePanel);
            }
        }
    }

    /**
     * Gets the name of the view.
     * @return The view name.
     */
    public String getName() {
        return viewName;
    }
}
