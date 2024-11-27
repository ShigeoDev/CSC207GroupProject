package view;

import interface_adapter.DishType.DishTypeController;
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

public class HomepageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String name = "Homepage";
    private final HomepageViewModel homepageViewModel;

    private HomepageController homepageController;
    private StoreRecipeController storeRecipeController;
    private MealPlanController mealPlanController;
    private DishTypeController dishTypeController;

    final JButton SavedRecipes;
    final JButton SearchRecipes;
    final JButton MealPlan;
    final JButton DishType;
    final JButton GetCalories;

    /**
     * A window with a title and a JButton.
     */
    public HomepageView(HomepageViewModel homepageViewModel) {
        this.homepageViewModel = homepageViewModel;
        this.homepageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Homepage Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        SavedRecipes = new JButton(homepageViewModel.Saved_BUTTON_LABEL);
        SearchRecipes = new JButton(homepageViewModel.Search_BUTTON_LABEL);
        MealPlan = new JButton(homepageViewModel.MealPlan_BUTTON_LABEL);
        DishType = new JButton("Search By Dish Type");
        GetCalories = new JButton(homepageViewModel.GetCalories_BUTTON_LABEL);

        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(MealPlan);
        buttons.add(SearchRecipes);
        buttons.add(SavedRecipes);
        buttons.add(DishType);
        buttons.add(GetCalories);

        GetCalories.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final HomepageState currentState = homepageViewModel.getState();
                        homepageController.getCalories(currentState.getUsername());
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
        SearchRecipes.addActionListener(this);

        /**
         * Add dish type button listener.
         */
        DishType.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dishTypeController.switchToDishType();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
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
        HomepageState state = (HomepageState) evt.getNewValue();
        // setFields(state);
    }

    public void setHomepageController(HomepageController controller) {
        this.homepageController = controller;
    }
    public void setMealPlanController(MealPlanController controller) {
        this.mealPlanController = controller;
    }
    public void setStoreRecipeController(StoreRecipeController controller) {
        this.storeRecipeController = controller;
    }

    /**
     * Set Dish Type Controller.
     */
    public void setDishTypeController(DishTypeController controller) {
        this.dishTypeController = controller;
    }
}


