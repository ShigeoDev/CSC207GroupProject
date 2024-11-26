package view;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReturnCaloriesView extends JPanel implements PropertyChangeListener {
    private final GetCaloriesViewModel getCaloriesViewModel;
    private final JLabel caloriesLabel;
    private final JPanel caloriesPanel = new JPanel();
    private GetCaloriesController getCaloriesController;

    public ReturnCaloriesView(GetCaloriesViewModel getCaloriesViewModel) {
        this.getCaloriesViewModel = getCaloriesViewModel;
        this.getCaloriesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Calories Result");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        caloriesLabel = new JLabel();
        caloriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(caloriesPanel);
        this.add(caloriesLabel);
    }
    public String getName() {
        return "Calorie Result";
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GetCaloriesState state = (GetCaloriesState) evt.getNewValue();
        final RecipeSavePanel recipe = new RecipeSavePanel(state.getRecipeObject());
        recipe.getSaveButton().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getCaloriesController.saveRecipe(state.getRecipeObject(), state.getUsername());
                    }
                }
        );
        caloriesPanel.add(recipe);
        caloriesLabel.setText(String.format("%s contains %d calories",
                state.getRecipeName(), state.getCalories()));
    }

    public void setController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }
}