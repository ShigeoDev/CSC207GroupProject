package app;

import javax.swing.*;

/*
Run this to run program
 */
public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomepageView()
                .addStoreRecipeView()
                .addHomepageUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
