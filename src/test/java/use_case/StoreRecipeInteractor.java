package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.Test;

import use_case.store_recipe.StoreRecipeInputData;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import use_case.store_recipe.StoreRecipeOutputData;

public class StoreRecipeInteractor {

    @Test
    public void SuccessHome() {
        StoreRecipeInputData inputData = new StoreRecipeInputData("test");
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();
        User test = new User("test", "test");
        dao.saveUser(test);

        StoreRecipeOutputBoundary presenter = new StoreRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }

            @Override
            public void goHome() {

            }
        }

        
         
    }
}
