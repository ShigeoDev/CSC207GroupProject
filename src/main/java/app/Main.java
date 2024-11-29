package app;

import javax.swing.*;

/*
Run this to run program
 */
public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addSignupView()
                .addLoginView()
                .addHomepageView()
                .addStoreRecipeView()
                .addMealPlanView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addHomepageUseCase()
                .addStoreRecipeUseCase()
                .addMealPlanUseCase()
                .addNutritionFilterPageView()
                .addNutritionFilterPageUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
