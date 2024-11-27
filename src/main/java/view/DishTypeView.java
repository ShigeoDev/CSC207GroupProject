package view;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypeState;
import interface_adapter.DishType.DishTypeViewModel;
import interface_adapter.store_recipe.StoreRecipeController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The DishTypeView class represents the GUI for selecting dish types and
 * displaying recipe results based on user input. It allows the user to select dish types using checkboxes
 * and search for corresponding recipes. The results are displayed in a scrollable panel.
 * This class listens for changes in the dish type view model and updates the view accordingly.
 */
public class DishTypeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final DishTypeViewModel dishTypeViewModel;
    private String viewName = "Dish Type";  // The name of the view for identifying purposes
    private final JLabel dishType = new JLabel("Select Dish Types:");  // Label for the dish type selection
    private final JButton searchButton = new JButton("Search");  // Button to trigger search
    private DishTypeController dishTypeController;  // Controller for handling dish type logic
    private StoreRecipeController storeRecipeController;
    private final JPanel resultsPanel = new JPanel();  // Panel to display search results
    private final JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);  // Scrollable panel for results
    private final List<JCheckBox> checkBoxList = new ArrayList<>();  // List of checkboxes for dish type selection
    private final String[] dishTypes = {
            "Biscuits and cookies", "Bread", "Cereals", "Condiments and sauces", "Desserts",
            "Drinks", "Main course", "Pancake", "Preps", "Preserve", "Salad", "Sandwiches",
            "Side dish", "Soup", "Starter", "Sweets"
    };  // Available dish types
    private JSONArray recipes;  // Stores the fetched recipes

    /**
     * Constructor to initialize the DishTypeView and set up the UI components.
     * It creates checkboxes for each dish type and adds them to the panel, along with the search button
     * and results display area.
     *
     * @param dishTypeViewModel the view model that holds the state for the dish type view
     */
    public DishTypeView(DishTypeViewModel dishTypeViewModel) {
        dishType.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center-align the label
        this.dishTypeViewModel = dishTypeViewModel;
        this.dishTypeViewModel.addPropertyChangeListener(this);  // Register for property changes

        // Create checkboxes for each dish type
        final JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 2));  // Arrange checkboxes in a grid layout

        for (String type : dishTypes) {
            JCheckBox checkBox = new JCheckBox(type);
            checkBoxList.add(checkBox);
            checkBoxPanel.add(checkBox);
        }

        // Button panel with search button
        final JPanel buttons = new JPanel();
        buttons.add(searchButton);

        // Set action listener for search button
        searchButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        String selectedTypes = getSelectedDishTypes();
                        recipes = dishTypeController.execute(selectedTypes);  // Call the controller to fetch recipes
                        updateResultsPanel(recipes);  // Update the results panel
                    }
                }
        );

        // Setup results panel layout and scroll pane
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScrollPane.setPreferredSize(new Dimension(400, 300));  // Set preferred size for scroll pane

        // Main layout for the DishTypeView
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(dishType);
        this.add(checkBoxPanel);
        this.add(buttons);
        this.add(resultsScrollPane);
    }

    /**
     * Handles button click events (not used in this specific class but implemented for ActionListener).
     *
     * @param evt the ActionEvent triggered by a button click
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());  // Print the action command of the clicked button
    }

    /**
     * Reacts to property changes from the DishTypeViewModel.
     * If the new state contains error information, no further action is taken.
     * If the new state contains recipe data, it updates the UI with the new recipes.
     *
     * @param evt the property change event triggered by the view model
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DishTypeState state = (DishTypeState) evt.getNewValue();
        if (state.getError() != null) {
            return;  // If an error is present, no action is taken
        }

        // If state contains recipe data, update the results panel
        JSONArray recipes = state.getRecipes();  // Retrieve recipes from the state
        if (recipes != null) {
            updateResultsPanel(recipes);  // Update the results panel with the new recipes
        }
    }

    /**
     * Retrieves the selected dish types as a comma-separated string.
     *
     * @return a string containing the names of selected dish types
     */
    private String getSelectedDishTypes() {
        StringBuilder selectedTypes = new StringBuilder();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                selectedTypes.append(checkBox.getText()).append(",");
            }
        }
        // Remove trailing comma if present
        if (selectedTypes.length() > 0) selectedTypes.setLength(selectedTypes.length() - 1);
        return selectedTypes.toString();
    }

    /**
     * Sets the DishTypeController for handling user interactions with the dish type view.
     *
     * @param controller the DishTypeController to set
     */
    public void setDishTypeController(DishTypeController controller) {
        this.dishTypeController = controller;
    }

    public void setStoreRecipeController(StoreRecipeController controller) {
        this.storeRecipeController = controller;
    }

    /**
     * Retrieves the name of the view (used for view identification).
     *
     * @return the name of the view
     */
    public String getName() {
        return this.viewName;
    }

    /**
     * Displays a dialog with a list of recipes found based on the search criteria.
     *
     * @param recipes a JSONArray containing the recipe data to display
     */
    public void showRecipesDialog(JSONArray recipes) {
        StringBuilder message = new StringBuilder("Recipes Found:\n\n");

        // Build the message to display each recipe's label
        for (int i = 0; i < recipes.length(); i++) {
            try {
                JSONObject recipe = recipes.getJSONObject(i);
                message.append("- ").append(recipe.getString("label")).append("\n");
            } catch (JSONException e) {
                message.append("- Error parsing recipe at index ").append(i).append("\n");
            }
        }

        // Display the results in a message dialog
        JOptionPane.showMessageDialog(this, message.toString(),
                "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Updates the results panel with the given recipes.
     * Each recipe is displayed as a label in the results panel.
     *
     * @param recipes the recipes to display in the results panel
     */
    private void updateResultsPanel(JSONArray recipes) {
        resultsPanel.removeAll();  // Clear existing results

        resultsPanel.add(new JLabel("Recipes Found:"));

        // Add each recipe label to the results panel
        for (int i = 0; i < recipes.length(); i++) {
            try {
                JSONObject recipe = recipes.getJSONObject(i).getJSONObject("recipe");
                RecipeSavePanel recipePanel = new RecipeSavePanel(recipe);
                resultsPanel.add(recipePanel);
                recipePanel.getSaveButton().addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                storeRecipeController.execute(recipe, dishTypeViewModel.getState().getUsername());
                            }
                        }
                );
            } catch (JSONException e) {
                resultsPanel.add(new JLabel("- Error parsing recipe at index " + i));
            }
        }

        // Revalidate and repaint the panel to reflect the changes
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
