package view;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.store_recipe.StoreRecipeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A view class that displays the calorie information for a recipe.
 * This panel shows the retrieved recipe information.
 * Also provides the option to save the recipe or to return home.
 *
 */
public class ReturnCaloriesView extends JPanel implements PropertyChangeListener {
    private final GetCaloriesViewModel getCaloriesViewModel;
    private final JLabel caloriesLabel;
    private final JPanel caloriesPanel = new JPanel();
    private GetCaloriesController getCaloriesController;
    private StoreRecipeController storeRecipeController;
    private HomepageController homepageController;
    private final JButton backButton;

    /**
     * Constructs a new ReturnCaloriesView.
     * @param getCaloriesViewModel The view model containing the calorie data
     */
    public ReturnCaloriesView(GetCaloriesViewModel getCaloriesViewModel) {
        this.getCaloriesViewModel = getCaloriesViewModel;
        this.getCaloriesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Calories Result");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        caloriesLabel = new JLabel();
        caloriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton = new JButton("Back to home");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backButton)) {
                    homepageController.execute();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(caloriesPanel);
        this.add(caloriesLabel);
        this.add(backButton);
    }

    /**
     * Getter for the name of the view.
     * @return The view name "Calorie Result"
     */
    public String getName() {
        return "Calorie Result";
    }

    /**
     * Handles updates to the view model state.
     * Updates the display with the new recipe and calorie information.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GetCaloriesState state = (GetCaloriesState) evt.getNewValue();

        if (state.getRecipeObject() != null) {
            final RecipeSavePanel recipe = new RecipeSavePanel(state.getRecipeObject());
            recipe.getSaveButton().addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            storeRecipeController.execute(state.getRecipeObject(), state.getUsername());
                        }
                    }
            );
            caloriesPanel.add(recipe);
            caloriesLabel.setText(String.format("%s contains %d calories",
                    state.getRecipeName(), state.getCalories()));
        }
    }

    /**
     * Setter for the controller.
     * @param controller Controller for the Get Calories use case
     */
    public void setController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }

    /**
     * Setter for the controller.
     * @param storeRecipeController Controller for the store recipes use case
     */
    public void setStoreRecipeController(StoreRecipeController storeRecipeController) {
        this.storeRecipeController = storeRecipeController;
    }

    /**
     * Setter for the controller.
     * @param homepageController Controller to handle navigation back to the homepage
     */
    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }
}