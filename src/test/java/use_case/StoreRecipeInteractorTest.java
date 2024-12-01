package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import use_case.store_recipe.StoreRecipeInputData;
import use_case.store_recipe.StoreRecipeInteractor;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import use_case.store_recipe.StoreRecipeOutputData;

/**
 * StoreRecipeInteractorTest class contains tests for the StoreRecipeInteractor class.
 */
public class StoreRecipeInteractorTest {

    /**
     * Test case to verify the execution of storing a recipe for a user.
     */
    @Test
    public void executeTest() {
        // Creating a recipe as a JSON object.
        String recipeString = "{\"recipe\" : \"Chicken\"}";
        JSONObject recipe = new JSONObject(recipeString);

        // Creating input data to store the recipe for the user "test".
        StoreRecipeInputData inputData = new StoreRecipeInputData("test", recipe);

        // Using an in-memory data access object (DAO) to save user and recipe data.
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();
        User test = new User("test", "test");
        dao.saveUser(test);
        dao.saveRecipe(recipe, test.getName());

        // Mocking the presenter for the success scenario.
        StoreRecipeOutputBoundary presenter = new StoreRecipeOutputBoundary() {
            @Override
            public void goView(StoreRecipeOutputData outputData) {
                // No specific action in this test case for the presenter.
            }
        };

        // Creating the interactor and executing the use case.
        StoreRecipeInteractor interactor = new StoreRecipeInteractor(dao, presenter);
        interactor.execute(inputData);

        // Verifying that the recipe has been correctly saved in the DAO.
        assertEquals(dao.getRecipes(test.getName()).get(0), recipe);
    }

    /**
     * Test case to verify the view logic for displaying stored recipe data.
     */
    @Test
    public void goViewTest() {
        // Creating a user for the test case.
        User test = new User("test", "test");
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        // Mocking the presenter to verify the output data.
        StoreRecipeOutputBoundary presenter = new StoreRecipeOutputBoundary() {
            @Override
            public void goView(StoreRecipeOutputData outputData) {
                // Verifying that the correct username is passed to the presenter.
                assertEquals(outputData.getUsername(), test.getName());
            }
        };

        // Creating the interactor and testing the goView method.
        StoreRecipeInteractor interactor = new StoreRecipeInteractor(dao, presenter);
        interactor.goView(test.getName());
    }
}
