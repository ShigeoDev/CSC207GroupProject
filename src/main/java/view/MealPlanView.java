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

public class MealPlanView extends JPanel implements PropertyChangeListener, ActionListener {

    private final MealPlanViewModel mealPlanViewModel;
    private StoreRecipeController storeRecipeController;
    private HomepageController homepageController;

    private String viewName = "MealPlan";

    private JLabel title = new JLabel("Meal Plan");
    private JPanel recipesPanel = new JPanel();
    private JButton home = new JButton("Home");

    public MealPlanView(MealPlanViewModel mealPlanViewModel) {
        this.mealPlanViewModel = mealPlanViewModel;
        this.mealPlanViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        recipesPanel.setLayout(new BoxLayout(recipesPanel, BoxLayout.Y_AXIS));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        home.setAlignmentX(Component.CENTER_ALIGNMENT);

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        homepageController.execute();
                    }
                }
        );

        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(recipesPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(home);
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public void setStoreRecipeController(StoreRecipeController storeRecipeController) {
        this.storeRecipeController = storeRecipeController;
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            recipesPanel.removeAll();
            final MealPlanState state = (MealPlanState) evt.getNewValue();
            final JSONObject[] recipes = state.getRecipes();
            final String[] mealnames = {"Breakfast: ", "Lunch: ", "Dinner: "};
            for (int i = 0; i < recipes.length; i++) {
                final JSONObject recipe = recipes[i];
                final RecipeSavePanel recipePanel = new RecipeSavePanel(recipe);
                recipePanel.getSaveButton().addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                storeRecipeController.execute(recipe, mealPlanViewModel.getState().getUser());
                            }
                        }
                );
                recipesPanel.add(recipePanel);
            }
        }
    }

    public String getName() {
       return viewName;
    }
}
