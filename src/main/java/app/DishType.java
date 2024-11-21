package app;

import javax.swing.*;

/*
Run this to run program
 */
public class DishType {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addDishTypeView()
                .addDishTypeUseCase()
                .search();

        application.pack();
        application.setVisible(true);
    }
}