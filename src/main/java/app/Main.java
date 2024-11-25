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
                .build();

        application.pack();
        application.setSize(1000,800);
        application.setVisible(true);
    }
}
