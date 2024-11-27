package use_case;

import data_access.ApiDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import interface_adapter.MealPlan.MealPlanPresenter;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.MealPlan.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealPlanInteractorTest {
    @Test
    public void executeTest() {

        String recipeString = "{\"recipe\" : \"Chicken\"}";
        JSONObject recipe = new JSONObject(recipeString);
        MealPlanDataAccessInterface dao = new InMemoryUserDataAccessObject();
        User test = new User("test", "test");
        MealPlanInputData inputData = new MealPlanInputData(test.getName());

        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "breakfast");
        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "lunch");
        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "dinner");

        MealPlanOutputBoundary presenter = new MealPlanOutputBoundary() {
            @Override
            public void prepareSuccessView(MealPlanOutputData mealPlanOutputData) {
                assertEquals(mealPlanOutputData.getRecipes()[0], recipe);
                assertEquals(mealPlanOutputData.getUsername(), test.getName());
            }
        };

        MealPlanInteractor interactor = new MealPlanInteractor(presenter, dao);
        interactor.execute(inputData);
    }
}
