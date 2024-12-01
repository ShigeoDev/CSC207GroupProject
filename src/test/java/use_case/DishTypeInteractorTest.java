package use_case;

import data_access.ApiDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import interface_adapter.DishType.DishTypePresenter;
import interface_adapter.DishType.DishTypeViewModel;
import interface_adapter.Homepage.HomepageViewModel;
import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.searchByDishType.*;
import use_case.store_recipe.StoreRecipeInputData;
import use_case.store_recipe.StoreRecipeInteractor;
import use_case.store_recipe.StoreRecipeOutputBoundary;
import use_case.store_recipe.StoreRecipeOutputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the `DishTypeInteractor` use case.
 * Ensures correct functionality for searching recipes by dish type and switching views.
 */
public class DishTypeInteractorTest {

    // Main use case class for handling dish type functionality
    private DishTypeInteractor dishTypeInteractor;
    // Interface for accessing recipe data
    private DishTypeUserDataAccessInterface userDataAccessInterface;
    // Interface for updating the UI
    private DishTypeOutputBoundary userPresenter;
    // View model for dish type
    private DishTypeViewModel viewModel;
    // Manages navigation between views
    private ViewManagerModel viewManagerModel;
    // View model for the homepage
    private HomepageViewModel homepageViewModel;

    // Holds the recipes returned from the interactor
    private JSONArray recipes;
    // Dish type used for testing
    String dishType = "Salad";
    // Input data object containing the dish type
    DishTypeInputData inputData = new DishTypeInputData(dishType);

    String recipeString = "{\"recipe\" : \"Salad\", \"dishType\" : \"salad\"}";
    JSONObject recipe = new JSONObject(recipeString);
    private InMemoryUserDataAccessObject dao;
    User test = new User("test", "test");

    @BeforeEach
    public void setUp() {
        // Initialize dependencies and set up the DishTypeInteractor instance

        // Simulates accessing recipe data from an API
        userDataAccessInterface = new ApiDataAccessObject();
        // Creates a new view model for dish type
        viewModel = new DishTypeViewModel();
        // Sets up view manager model
        viewManagerModel = new ViewManagerModel();
        // Creates a view model for the homepage
        homepageViewModel = new HomepageViewModel();
        // Presenter for updating views
        userPresenter = new DishTypePresenter(viewModel, viewManagerModel, homepageViewModel);
        dao = new InMemoryUserDataAccessObject();
        dao.save(recipe, test.getName());
        // Main interactor instance
        dishTypeInteractor = new DishTypeInteractor(dao, userPresenter);
    }

    @Test
    public void test_execute() {
        // Test searching recipes by dish type


        // Execute the interactor with input data
        recipes = dishTypeInteractor.execute(inputData);

        // Counter for recipes do not match the dish type
        int count = 0;
        for (int i = 0; i < recipes.length(); i++) {
            // Get the recipe object
            String recipe = recipes.getJSONObject(i).getString("dishType");
            assertEquals("salad", recipe);
        }
    }

    @Test
    public void testSwitchToDishType() {
        // Test switching the view to the dish type view

        // Create a test user
        User test = new User("test", "test");

        // DishTypeOutputBoundary
        DishTypeOutputBoundary presenter = new DishTypeOutputBoundary() {
            @Override
            public void prepareSuccessView(DishTypeOutputData outputData) {
            }

            @Override
            public void prepareFailView(DishTypeOutputData outputData) {
            }

            @Override
            public void prepareDishType(String username) {
                // Check that the username is passed correctly
                assertEquals(username, test.getName());
            }

            @Override
            public void prepareHomepage(String username) {
            }
        };
        // Create interactor with mocked presenter
        DishTypeInteractor interactor = new DishTypeInteractor(userDataAccessInterface, presenter);
        // Call the method with the test user's name
        interactor.switchToDishType(test.getName());
    }

    @Test
    public void testSwitchToHomepage() {
        // Test switching the view to the homepage

        // Create a test user
        User test = new User("test", "test");

        // Mock presenter to validate interaction
        DishTypeOutputBoundary presenter = new DishTypeOutputBoundary() {
            @Override
            public void prepareSuccessView(DishTypeOutputData outputData) {
            }

            @Override
            public void prepareFailView(DishTypeOutputData outputData) {
            }

            @Override
            public void prepareDishType(String username) {
            }

            @Override
            public void prepareHomepage(String username) {
                // Check that the username is passed correctly
                assertEquals(username, test.getName());
            }
        };
        // Create interactor with mocked presenter
        DishTypeInteractor interact = new DishTypeInteractor(userDataAccessInterface, presenter);
        // Call the method with the test username
        interact.switchToHomepage(test.getName());
    }
}


