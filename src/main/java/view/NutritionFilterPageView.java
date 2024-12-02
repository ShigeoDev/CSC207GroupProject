package view;

import interface_adapter.NutritionFilterPage.NutritionFilterPageController;
import interface_adapter.NutritionFilterPage.NutritionFilterPageState;
import interface_adapter.NutritionFilterPage.NutritionFilterPageViewModel;
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
 * The View for the Nutrition Filter Page.
 */
public class NutritionFilterPageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Nutrition Filter";
    private JSONArray recipes;
    private final String[] nutrients = {
            "Vitamin A", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3",
            "Vitamin B6", "Vitamin B12", "Vitamin D", "Vitamin E", "Vitamin K",
            "CARBOHYDRATES", "FAT", "SUGAR", "FIBER", "PROTEIN",
            "IRON", "CALCIUM", "POTASSIUM", "SODIUM", "MAGNESIUM", "PHOSPHORUS"
    };

    private final NutritionFilterPageViewModel viewModel;
    private NutritionFilterPageController controller;
    private StoreRecipeController storeRecipeController;

    private final JLabel nutrient = new JLabel("Select Nutrients:");
    private final JButton submitButton = new JButton("Find Recipes");
    private final JButton homeButton = new JButton("Home");
    private final JPanel resultsPanel = new JPanel();
    private final JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);
    private final List<JCheckBox> checkBoxList = new ArrayList<>();

    /**
     * Constructs a new {@code NutritionFilterPageView} with the specified view model.
     * <p>
     * This constructor initializes the Nutrition Filter Page view, setting up the UI components
     * required for user interaction. It displays nutrient selection options, buttons for submitting the request,
     * and a panel for showing the search results.
     * </p>
     *
     * <p>
     * The view listens to changes in the {@code NutritionFilterPageViewModel} and updates the displayed results accordingly.
     * It also handles button actions for submitting selected nutrients and navigating to the homepage.
     * </p>
     *
     * @param nutritionFilterPageViewModel the {@code NutritionFilterPageViewModel} used to manage the state of the Nutrition Filter Page view
     */
    public NutritionFilterPageView(NutritionFilterPageViewModel nutritionFilterPageViewModel) {
        nutrient.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewModel = nutritionFilterPageViewModel;
        this.viewModel.addPropertyChangeListener(this);

        final JPanel checkBoxPanel = new JPanel();

        checkBoxPanel.setLayout(new GridLayout(0, 2));

        for (String nutrient : nutrients) {
            JCheckBox checkBox = new JCheckBox(nutrient);
            checkBoxList.add(checkBox);
            checkBoxPanel.add(checkBox);
        }

        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttons.add(submitButton);
        buttons.add(homeButton);

        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final NutritionFilterPageState currentState = viewModel.getState();
                        controller.switchToHomepage(currentState.getUsername());
                    }
                }
        );

        submitButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitButton)) {
                        ArrayList<String> selectedNutrients = getSelectedNutrients();
                        recipes = controller.execute(selectedNutrients);
                        updateResultsPanel(recipes);
                    }
                }
        );

        // Results Panel inside Scroll Pane
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScrollPane.setPreferredSize(new Dimension(400, 300));

        // Set the main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        nutrient.setAlignmentX(Component.CENTER_ALIGNMENT);
        nutrient.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(nutrient);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nutrient Selection Panel
        this.add(checkBoxPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Buttons Panel
        this.add(buttons);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(resultsScrollPane);
    }

    /**
     * Handles button click events (not used in this specific class but implemented for ActionListener).
     *
     * @param evt the ActionEvent triggered by a button click
     */
    public void actionPerformed(ActionEvent evt) {
        // Print the action command of the clicked button
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property change events from the {@code NutritionFilterPageViewModel}.
     * <p>
     * This method is called whenever the state of the view model changes. It checks the new state for
     * errors and updates the results panel if there are any new recipes available. If an error is detected,
     * no action is taken, and the method returns early.
     * </p>
     *
     * @param evt the {@code PropertyChangeEvent} containing the updated state of the view model
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NutritionFilterPageState state = (NutritionFilterPageState) evt.getNewValue();
        if (state.getSearchError() != null) {
            return;  // If an error is present, no action is taken
        }

        // If state contains recipe data, update the results panel
        JSONArray recipes = state.getRecipeDetails();  // Retrieve recipes from the state
        if (recipes != null) {
            updateResultsPanel(recipes);  // Update the results panel with the new recipes
        }
    }

    /**
     * Retrieves the list of nutrients selected by the user.
     * <p>
     * This method iterates over the list of checkboxes representing different nutrients.
     * If a checkbox is selected, the corresponding nutrient name is added to the result list.
     * </p>
     *
     * @return an {@code ArrayList<String>} containing the names of the nutrients selected by the user;
     *         may be empty if no checkboxes are selected
     */
    private ArrayList<String> getSelectedNutrients() {
        ArrayList<String> selectedNutrients = new ArrayList<>();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                selectedNutrients.add(checkBox.getText());
            }
        }

        return selectedNutrients;
    }

    /**
     * Sets the controller for this view.
     * <p>
     * The controller handles user actions and interacts with the interactor (use case).
     * This method establishes the connection between the view and its controller.
     * @param controller the {@code NutritionFilterPageController} to handle user actions
     */
    public void setController(NutritionFilterPageController controller) {
        this.controller = controller;
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
                                storeRecipeController.execute(recipe, viewModel.getState().getUsername());
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
