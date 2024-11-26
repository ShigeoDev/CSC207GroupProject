package view;

import interface_adapter.DishType.DishTypeController;
import interface_adapter.DishType.DishTypeState;
import interface_adapter.DishType.DishTypeViewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DishTypeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final DishTypeViewModel dishTypeViewModel;
    private String viewName = "Dish Type";
    private final JLabel dishType = new JLabel("Dish Type");
    private final JTextArea dishTypeInputField = new JTextArea();
    private final JButton searchButton = new JButton("Search");
    private DishTypeController dishTypeController;
    private final JPanel resultsPanel = new JPanel();
    private final JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);
    private JSONArray recipes;

    public DishTypeView(DishTypeViewModel dishTypeViewModel) {

        dishType.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.dishTypeViewModel = dishTypeViewModel;
        this.dishTypeViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        buttons.add(searchButton);

        searchButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        recipes = dishTypeController.execute(dishTypeInputField.getText());
                        updateResultsPanel(recipes);
                    }
                }
        );
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScrollPane.setPreferredSize(new Dimension(400, 300));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(dishType);
        this.add(dishTypeInputField);
        this.add(buttons);
        this.add(resultsScrollPane);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DishTypeState state = (DishTypeState) evt.getNewValue();
        state.setRecipes(dishTypeController.execute(dishTypeInputField.getText()));
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If state contains recipe data, update the results panel
        JSONArray recipes = state.getRecipes(); // Assuming `state.getRecipes()` returns a JSONArray
        if (recipes != null) {
            updateResultsPanel(recipes);
        }
    }

    private void setFields(DishTypeState state) {
        dishTypeInputField.setText(state.getDishType());
    }

    public void setDishTypeController(DishTypeController controller) {
        this.dishTypeController = controller;
    }

    public String getName(){
        return this.viewName;
    }

    public void showRecipesDialog(JSONArray recipes) {
        StringBuilder message = new StringBuilder("Recipes Found:\n\n");

        for (int i = 0; i < recipes.length(); i++) {
            try {
                JSONObject recipe = recipes.getJSONObject(i);
                message.append("- ").append(recipe.getString("label")).append("\n");
            } catch (JSONException e) {
                message.append("- Error parsing recipe at index ").append(i).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, message.toString(),
                "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateResultsPanel(JSONArray recipes) {
        resultsPanel.removeAll();

        resultsPanel.add(new JLabel("Recipes Found:"));

        for (int i = 0; i < recipes.length(); i++) {
            try {
                JSONObject recipe = recipes.getJSONObject(i);
                String label = recipe.getJSONObject("recipe").getString("label");
                resultsPanel.add(new JLabel("- " + label));
            } catch (JSONException e) {
                resultsPanel.add(new JLabel("- Error parsing recipe at index " + i));
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }



}
