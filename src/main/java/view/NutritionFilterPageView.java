package view;

import interface_adapter.NutritionFilterPage.NutritionFilterPageController;
import interface_adapter.NutritionFilterPage.NutritionFilterPageState;
import interface_adapter.NutritionFilterPage.NutritionFilterPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The View for the Nutrition Filter Page.
 */
public class NutritionFilterPageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Nutrition Filter";

    private final NutritionFilterPageViewModel viewModel;

    private NutritionFilterPageController controller;

    private final JCheckBox vitaminACheckBox = new JCheckBox("Vitamin A");
    private final JCheckBox vitaminCCheckBox = new JCheckBox("Vitamin C");
    private final JCheckBox vitaminB1CheckBox = new JCheckBox("Vitamin B1");
    private final JCheckBox vitaminB2CheckBox = new JCheckBox("Vitamin B2");
    private final JCheckBox vitaminB3CheckBox = new JCheckBox("Vitamin B3");
    private final JCheckBox vitaminB6CheckBox = new JCheckBox("Vitamin B6");
    private final JCheckBox vitaminB12CheckBox = new JCheckBox("Vitamin B12");
    private final JCheckBox vitaminDCheckBox = new JCheckBox("Vitamin D");
    private final JCheckBox vitaminECheckBox = new JCheckBox("Vitamin E");
    private final JCheckBox vitaminKCheckBox = new JCheckBox("Vitamin K");
    private final JCheckBox carbsCheckBox = new JCheckBox("CARBOHYDRATES");
    private final JCheckBox fatCheckBox = new JCheckBox("FAT");
    private final JCheckBox sugarCheckBox = new JCheckBox("SUGAR");
    private final JCheckBox fiberCheckBox = new JCheckBox("Fiber");
    private final JCheckBox proteinCheckBox = new JCheckBox("Protein");
    private final JCheckBox ironCheckBox = new JCheckBox("Iron");
    private final JCheckBox calciumCheckBox = new JCheckBox("Calcium");
    private final JCheckBox potassiumCheckBox = new JCheckBox("POTASSIUM");
    private final JCheckBox sodiumCheckBox = new JCheckBox("SODIUM");
    private final JCheckBox magnesiumCheckBox = new JCheckBox("Magnesium");
    private final JCheckBox phosphorusCheckBox = new JCheckBox("PHOSPHORUS");

    private final JLabel errorLabel = new JLabel();

    private final JTextArea resultsArea = new JTextArea();

    private final JButton submitButton;

    public NutritionFilterPageView(NutritionFilterPageViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Nutrition Filter Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nutrient selection panel
        final JPanel nutrientPanel = new JPanel();
        nutrientPanel.setLayout(new GridLayout(0, 1));
        nutrientPanel.add(vitaminACheckBox);
        nutrientPanel.add(vitaminCCheckBox);
        nutrientPanel.add(vitaminB1CheckBox);
        nutrientPanel.add(vitaminB2CheckBox);
        nutrientPanel.add(vitaminB3CheckBox);
        nutrientPanel.add(vitaminB6CheckBox);
        nutrientPanel.add(vitaminB12CheckBox);
        nutrientPanel.add(vitaminDCheckBox);
        nutrientPanel.add(vitaminECheckBox);
        nutrientPanel.add(vitaminKCheckBox);
        nutrientPanel.add(carbsCheckBox);
        nutrientPanel.add(fatCheckBox);
        nutrientPanel.add(sugarCheckBox);
        nutrientPanel.add(fiberCheckBox);
        nutrientPanel.add(proteinCheckBox);
        nutrientPanel.add(ironCheckBox);
        nutrientPanel.add(calciumCheckBox);
        nutrientPanel.add(potassiumCheckBox);
        nutrientPanel.add(sodiumCheckBox);
        nutrientPanel.add(magnesiumCheckBox);
        nutrientPanel.add(phosphorusCheckBox);


        // Error label
        errorLabel.setForeground(Color.RED);

        // Submit button
        submitButton = new JButton("Find Recipes");

        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(nutrientPanel);
        this.add(errorLabel);
        this.add(submitButton);
        this.add(scrollPane);

        submitButton.addActionListener(this);

        // Update the ViewModel when checkboxes change
        vitaminACheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminCCheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB1CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB2CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB3CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB6CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminB12CheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminDCheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminECheckBox.addActionListener(e -> updateSelectedNutrients());
        vitaminKCheckBox.addActionListener(e -> updateSelectedNutrients());
        carbsCheckBox.addActionListener(e -> updateSelectedNutrients());
        fatCheckBox.addActionListener(e -> updateSelectedNutrients());
        sugarCheckBox.addActionListener(e -> updateSelectedNutrients());
        fiberCheckBox.addActionListener(e -> updateSelectedNutrients());
        proteinCheckBox.addActionListener(e -> updateSelectedNutrients());
        ironCheckBox.addActionListener(e -> updateSelectedNutrients());
        calciumCheckBox.addActionListener(e -> updateSelectedNutrients());
        potassiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        sodiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        magnesiumCheckBox.addActionListener(e -> updateSelectedNutrients());
        phosphorusCheckBox.addActionListener(e -> updateSelectedNutrients());
    }

    private void updateSelectedNutrients() {
        ArrayList<String> selectedNutrients = new ArrayList<>();
        if (vitaminACheckBox.isSelected()) {
            selectedNutrients.add("Vitamin A");
        }
        if (vitaminCCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin C");
        }
        if (vitaminB1CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B1");
        }
        if (vitaminB2CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B2");
        }
        if (vitaminB3CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B3");
        }
        if (vitaminB6CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B6");
        }
        if (vitaminB12CheckBox.isSelected()) {
            selectedNutrients.add("Vitamin B12");
        }
        if (vitaminDCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin D");
        }
        if (vitaminECheckBox.isSelected()) {
            selectedNutrients.add("Vitamin E");
        }
        if (vitaminKCheckBox.isSelected()) {
            selectedNutrients.add("Vitamin K");
        }
        if (carbsCheckBox.isSelected()) {
            selectedNutrients.add("CARBOHYDRATES");
        }
        if (fatCheckBox.isSelected()) {
            selectedNutrients.add("FAT");
        }
        if (sugarCheckBox.isSelected()) {
            selectedNutrients.add("SUGAR");
        }
        if (fiberCheckBox.isSelected()) {
            selectedNutrients.add("Fiber");
        }
        if (proteinCheckBox.isSelected()) {
            selectedNutrients.add("Protein");
        }
        if (ironCheckBox.isSelected()) {
            selectedNutrients.add("Iron");
        }
        if (calciumCheckBox.isSelected()) {
            selectedNutrients.add("Calcium");
        }
        if (potassiumCheckBox.isSelected()) {
            selectedNutrients.add("POTASSIUM");
        }
        if (sodiumCheckBox.isSelected()) {
            selectedNutrients.add("SODIUM");
        }
        if (magnesiumCheckBox.isSelected()) {
            selectedNutrients.add("MAGNESIUM");
        }
        if (phosphorusCheckBox.isSelected()) {
            selectedNutrients.add("PHOSPHORUS");
        }

        NutritionFilterPageState currentState = viewModel.getState();
        if (currentState == null) {
            currentState = new NutritionFilterPageState();
        }
        currentState.setSelectedNutrients(selectedNutrients);
        viewModel.setState(currentState);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(submitButton)) {
            NutritionFilterPageState currentState = viewModel.getState();
            if (currentState != null && controller != null) {
                controller.execute(currentState.getSelectedNutrients());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NutritionFilterPageState state = (NutritionFilterPageState) evt.getNewValue();

        if (state.getSearchError() != null) {
            errorLabel.setText(state.getSearchError());
            resultsArea.setText("");
        } else if (state.getRecipeDetails() != null) {
            errorLabel.setText("");
            StringBuilder results = new StringBuilder();
            for (String recipeDetail : state.getRecipeDetails()) {
                results.append(recipeDetail);
            }
            resultsArea.setText(results.toString());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(NutritionFilterPageController controller) {
        this.controller = controller;
    }
}
