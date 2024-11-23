package view;

import interface_adapter.MealPlan.MealPlanController;
import interface_adapter.MealPlan.MealPlanState;
import interface_adapter.MealPlan.MealPlanViewModel;
import interface_adapter.store_recipe.StoreRecipeState;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MealPlanView extends JPanel implements PropertyChangeListener, ActionListener {

    private final MealPlanViewModel mealPlanViewModel;
    private MealPlanController mealPlanController;

    private String viewName = "MealPlan";

    private JLabel title = new JLabel("Meal Plan");
    private JPanel recipesPanel = new JPanel();

    public MealPlanView(MealPlanViewModel mealPlanViewModel) {
        this.mealPlanViewModel = mealPlanViewModel;
        this.mealPlanViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        recipesPanel.setLayout(new BoxLayout(recipesPanel, BoxLayout.Y_AXIS));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(recipesPanel);
    }

    public void setMealPlanController(MealPlanController mealPlanController) {
        this.mealPlanController = mealPlanController;
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final MealPlanState state = (MealPlanState) evt.getNewValue();
            final JSONArray recipes = state.getRecipes();
            for (int i = 0; i < 3; i++) {
                final JSONObject recipe = recipes.getJSONObject(i);
                System.out.println(recipe);
                final String recipeName = recipe.getJSONObject("recipe").getString("label");
                System.out.println(recipeName);
                final JLabel recipeLabel = new JLabel(recipeName);
                recipeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                recipesPanel.add(recipeLabel);
            }
        }
    }

    public String getName() {
       return viewName;
    }
}
