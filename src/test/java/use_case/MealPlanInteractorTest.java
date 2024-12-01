package use_case;

import data_access.ApiDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import interface_adapter.MealPlan.MealPlanPresenter;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.MealPlan.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * MealPlanInteractorTest class contains tests for the MealPlanInteractor class.
 */
public class MealPlanInteractorTest {

    /**
     * Test case to verify the execution of the MealPlanInteractor.
     */
    @Test
    public void executeTest() {

        // Creating a sample recipe as a JSON object.
        String recipeString = "{\"recipe\" : \"Chicken\"}";
        JSONObject recipe = new JSONObject(recipeString);

        // Creating an in-memory data access object (DAO) to store the recipe data.
        MealPlanDataAccessInterface dao = new InMemoryUserDataAccessObject();

        // Creating a test user.
        User test = new User("test", "test");

        // Preparing input data for the interactor with the user's name.
        MealPlanInputData inputData = new MealPlanInputData(test.getName());

        // Setting the recipe for each meal (breakfast, lunch, dinner) for the user.
        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "breakfast");
        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "lunch");
        ((InMemoryUserDataAccessObject) dao).setRecipebyMeal(recipe, "dinner");

        // Mocking the presenter that handles the success view preparation.
        MealPlanOutputBoundary presenter = new MealPlanOutputBoundary() {
            @Override
            public void prepareSuccessView(MealPlanOutputData mealPlanOutputData) {
                // Verifying that the recipes and username in the output data match the expected values.
                assertEquals(mealPlanOutputData.getRecipes()[0], recipe);
                assertEquals(mealPlanOutputData.getUsername(), test.getName());
            }
        };

        // Creating the MealPlanInteractor and executing the use case.
        MealPlanInteractor interactor = new MealPlanInteractor(presenter, dao);
        interactor.execute(inputData);
    }
}
