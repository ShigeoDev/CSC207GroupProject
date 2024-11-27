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

public class StoreRecipeInteractorTest {

    @Test
    public void executeTest() {
        String recipeString = "{\"recipe\" : \"Chicken\"}";
        JSONObject recipe = new JSONObject(recipeString);
        StoreRecipeInputData inputData = new StoreRecipeInputData("test", recipe);
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();
        User test = new User("test", "test");
        dao.saveUser(test);
        dao.saveRecipe(recipe, test.getName());

        StoreRecipeOutputBoundary presenter = new StoreRecipeOutputBoundary() {
            @Override
            public void goView(StoreRecipeOutputData outputData) {
            }
        };

        StoreRecipeInteractor interactor = new StoreRecipeInteractor(dao, presenter);
        interactor.execute(inputData);
        assertEquals(dao.getRecipes(test.getName()).get(0), recipe);
    }

    @Test
    public void goViewTest() {

        User test = new User("test", "test");
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        StoreRecipeOutputBoundary presenter = new StoreRecipeOutputBoundary() {
            @Override
            public void goView(StoreRecipeOutputData outputData) {
                assertEquals(outputData.getUsername(), test.getName());
            }
        };
        StoreRecipeInteractor interactor = new StoreRecipeInteractor(dao, presenter);
        interactor.goView(test.getName());
    }
}
