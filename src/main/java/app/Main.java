package app;

import javax.swing.*;

/*
Run this to run program
 */
public class Main {
    /**
     * The main method to run the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Create an instance of AppBuilder to construct the application
        final AppBuilder appBuilder = new AppBuilder();
        // Add views and use cases for the application
        final JFrame application = appBuilder
                // Add the Signup view to the application
                .addSignupView()
                // Add the Login view
                .addLoginView()
                // Add the Homepage view
                .addHomepageView()
                // Add the Store Recipe view
                .addStoreRecipeView()
                // Add the Meal Plan view
                .addMealPlanView()
                // Add the Dish Type view
                .addDishTypeView()
                // Add the Get Calories view
                .addGetCaloriesView()
                // Add the Signup use case
                .addSignupUseCase()
                // Add the Login use case
                .addLoginUseCase()
                // Add the Homepage use case
                .addHomepageUseCase()
                // Add the Store Recipe use case
                .addStoreRecipeUseCase()
                // Add the Meal Plan use case
                .addMealPlanUseCase()
                // Add the Dish Type use case
                .addDishTypeUseCase()
                // Add the Get Calories use case
                .addGetCaloriesUseCase()
                //Build the application
                .build();

        // Pack the components
        application.pack();
        // Set the size of the application window
        application.setSize(1000,800);
        // Make the application window visible
        application.setVisible(true);
    }
}
