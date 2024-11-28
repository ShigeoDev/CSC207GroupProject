package view;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.Homepage.HomepageController;
import interface_adapter.Homepage.HomepageState;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.MealPlan.MealPlanController;
import interface_adapter.store_recipe.StoreRecipeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Main homepage view of the app.
 * Displays buttons for various features.
 * Implements ActionListener to handle button clicks.
 * Implements PropertyChangeListener to respond to state changes.
 */
public class HomepageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "Homepage";
    private final HomepageViewModel homepageViewModel;

    private HomepageController homepageController;
    private StoreRecipeController storeRecipeController;
    private MealPlanController mealPlanController;
    private DishTypeController dishTypeController;
    private GetCaloriesController getCaloriesController;;

    final JButton SavedRecipes;
    final JButton MealPlan;
    final JButton DishType;
    final JButton GetCalories;

    /**
     * Constructs a new HomepageView.
     * Initializes the UI.
     * @param homepageViewModel View model containing the Homepage state and data
     */
    public HomepageView(HomepageViewModel homepageViewModel) {
        this.homepageViewModel = homepageViewModel;
        this.homepageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Homepage Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        SavedRecipes = new JButton(homepageViewModel.Saved_BUTTON_LABEL);
        MealPlan = new JButton(homepageViewModel.MealPlan_BUTTON_LABEL);
        DishType = new JButton("Search By Dish Type");
        GetCalories = new JButton(homepageViewModel.GetCalories_BUTTON_LABEL);

        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(MealPlan);
        buttons.add(SavedRecipes);
        buttons.add(DishType);
        buttons.add(GetCalories);

        GetCalories.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final HomepageState currentState = homepageViewModel.getState();
                        getCaloriesController.getCalories(currentState.getUsername());
                    }
                }
        );

        MealPlan.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final HomepageState currentState = homepageViewModel.getState();

                        mealPlanController.execute(currentState.getUsername());
                    }
                }
        );

        SavedRecipes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final HomepageState currentState = homepageViewModel.getState();

                        storeRecipeController.goView(currentState.getUsername());
                    }
                }
        );

        /**
         * Add dish type button listener.
         */
        DishType.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final HomepageState currentState = homepageViewModel.getState();
                        dishTypeController.switchToDishType(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * Getter for the view name.
     * @return Name of view
     */
    public String getName() {
        return this.name;
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Responds to propery change events from the view model.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomepageState state = (HomepageState) evt.getNewValue();
        // setFields(state);
    }

    /**
     * Sets the homepage controller.
     * @param controller Controller to handle homepage
     */
    public void setHomepageController(HomepageController controller) {
        this.homepageController = controller;
    }

    /**
     * Sets the meal plan controller.
     * @param controller Controller to handle Meal plan use case
     */
    public void setMealPlanController(MealPlanController controller) {
        this.mealPlanController = controller;
    }

    /**
     * Sets the store recipe controller.
     * @param controller Controller to handle store recipe use case
     */
    public void setStoreRecipeController(StoreRecipeController controller) {
        this.storeRecipeController = controller;
    }

    /**
     * Sets the dish type controller.
     * @param controller Controller to handle dish type use case
     */
    public void setDishTypeController(DishTypeController controller) {
        this.dishTypeController = controller;
    }

    /**
     * Sets the get calories controller.
     * @param controller Controller to handle get calories use case
     */
    public void setGetCaloriesController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }
}


