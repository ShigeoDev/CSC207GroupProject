package view;

import interface_adapter.GetCalories.GetCaloriesController;
import interface_adapter.GetCalories.GetCaloriesState;
import interface_adapter.GetCalories.GetCaloriesViewModel;
import interface_adapter.store_recipe.StoreRecipeController;

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
    private StoreRecipeController storeRecipeController;
    private final JButton backButton;

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
                    getCaloriesController.backToHome();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(caloriesPanel);
        this.add(caloriesLabel);
        this.add(backButton);
    }


    public String getName() {
        return "Calorie Result";
    }



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

    public void setController(GetCaloriesController controller) {
        this.getCaloriesController = controller;
    }
    public void setStoreRecipeController(StoreRecipeController storeRecipeController) {
        this.storeRecipeController = storeRecipeController;
    }
}