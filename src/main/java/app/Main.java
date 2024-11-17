package app;

import javax.swing.*;

/*
Run this to run program
 */
public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                .addHomepageView()
                .addStoreRecipeView()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
