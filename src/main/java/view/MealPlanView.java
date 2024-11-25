package view;

import interface_adapter.MealPlan.MealPlanController;
import interface_adapter.MealPlan.MealPlanState;
import interface_adapter.MealPlan.MealPlanViewModel;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MealPlanView extends JPanel implements PropertyChangeListener, ActionListener {

    private final MealPlanViewModel mealPlanViewModel;
    private MealPlanController mealPlanController;

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
                        mealPlanController.goHome();
                    }
                }
        );

        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(recipesPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(home);
    }

    public void setMealPlanController(MealPlanController mealPlanController) {
        this.mealPlanController = mealPlanController;
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
                final RecipePanel recipePanel = new RecipePanel(recipes[i]);
                recipesPanel.add(recipePanel);
            }
        }
    }

    public String getName() {
       return viewName;
    }
}
