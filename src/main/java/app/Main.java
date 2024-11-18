package app;

import javax.swing.*;

/*
Run this to run program
 */
public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addHomepageView()
                .addStoreRecipeView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addHomepageUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
