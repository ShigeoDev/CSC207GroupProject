package use_case;

import data_access.ApiDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.GetCalories.*;
import entity.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GetCaloriesInteractorTest {
    private ApiDataAccessObject mockApiDao;
    private InMemoryUserDataAccessObject userDao;
    private GetCaloriesOutputBoundary mockPresenter;
    private GetCaloriesInteractor interactor;

    @BeforeEach
    void setUp() {
        userDao = new InMemoryUserDataAccessObject();
        User testUser = new User("testUser", "password");
        userDao.saveUser(testUser);
    }

    @Test
    void successfulRecipeRetrieval() {
        // Arrange
        JSONObject recipeData = new JSONObject();
        recipeData.put("label", "ramen");
        recipeData.put("calories", 500);

        JSONObject hitObject = new JSONObject();
        hitObject.put("recipe", recipeData);

        JSONArray mockHits = new JSONArray();
        mockHits.put(hitObject);

        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                return mockHits;
            }
        };

        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {
                assertEquals("ramen", outputData.getRecipeName());
                assertEquals(500, outputData.getCalories());
                assertEquals(recipeData, outputData.getRecipeObject());
                assertEquals("testUser", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Should not have called prepareFailView");
            }

            @Override
            public void prepareHomeView() {
                fail("Should not have called prepareHomeView");
            }

            @Override
            public void prepareGetCaloriesView(String username) {
                fail("Should not have called prepareGetCaloriesView");
            }
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        GetCaloriesInputData inputData = new GetCaloriesInputData("ramen", "testUser");
        interactor.execute(inputData);
    }

    @Test
    void noRecipesFound() {
        // Arrange
        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                return new JSONArray(); // Empty array
            }
        };

        boolean[] noActionsTriggered = {true};
        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {
                noActionsTriggered[0] = false;
            }

            @Override
            public void prepareFailView(String error) {
                noActionsTriggered[0] = false;
            }

            @Override
            public void prepareHomeView() {
                noActionsTriggered[0] = false;
            }

            @Override
            public void prepareGetCaloriesView(String username) {
                noActionsTriggered[0] = false;
            }
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        GetCaloriesInputData inputData = new GetCaloriesInputData("Nonexistent Recipe", "testUser");
        interactor.execute(inputData);

        // Assert
        assertTrue(noActionsTriggered[0], "No presenter methods should be called when no recipes are found");
    }

    @Test
    void saveRecipeTest() {
        // Arrange
        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                return new JSONArray();
            }
        };

        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {}
            @Override
            public void prepareFailView(String error) {}
            @Override
            public void prepareHomeView() {}
            @Override
            public void prepareGetCaloriesView(String username) {}
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        JSONObject testRecipe = new JSONObject();
        testRecipe.put("label", "Test Recipe");
        testRecipe.put("calories", 300);
        interactor.saveRecipe(testRecipe, "testUser");

        // Assert
        ArrayList<JSONObject> savedRecipes = userDao.getRecipes("testUser");
        assertNotNull(savedRecipes);
        assertEquals(1, savedRecipes.size());
        assertEquals(testRecipe.toString(), savedRecipes.get(0).toString());
    }

    @Test
    void backToHomeNavigationTest() {
        // Arrange
        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                return new JSONArray();
            }
        };

        final boolean[] homeViewCalled = {false};
        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {
                fail("Should not have called prepareSuccessView");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Should not have called prepareFailView");
            }

            @Override
            public void prepareHomeView() {
                homeViewCalled[0] = true;
            }

            @Override
            public void prepareGetCaloriesView(String username) {
                fail("Should not have called prepareGetCaloriesView");
            }
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        interactor.backToHome();

        // Assert
        assertTrue(homeViewCalled[0], "Home view should have been called");
    }

    @Test
    void getCaloriesNavigationTest() {
        // Arrange
        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                return new JSONArray();
            }
        };

        final String[] receivedUsername = {null};
        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {
                fail("Should not have called prepareSuccessView");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Should not have called prepareFailView");
            }

            @Override
            public void prepareHomeView() {
                fail("Should not have called prepareHomeView");
            }

            @Override
            public void prepareGetCaloriesView(String username) {
                receivedUsername[0] = username;
            }
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        String testUsername = "testUser";
        interactor.getCalories(testUsername);

        // Assert
        assertEquals(testUsername, receivedUsername[0], "Username should be passed correctly");
    }

    @Test
    void apiErrorHandling() {
        // Arrange
        String expectedErrorMessage = "API Connection Failed";
        mockApiDao = new ApiDataAccessObject() {
            @Override
            public JSONArray getRecipebyName(String recipeName) {
                throw new RuntimeException(expectedErrorMessage);
            }
        };

        mockPresenter = new GetCaloriesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetCaloriesOutputData outputData) {
                fail("Should not have called prepareSuccessView");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(expectedErrorMessage, error, "Error message should match the exception");
            }

            @Override
            public void prepareHomeView() {
                fail("Should not have called prepareHomeView");
            }

            @Override
            public void prepareGetCaloriesView(String username) {
                fail("Should not have called prepareGetCaloriesView");
            }
        };

        interactor = new GetCaloriesInteractor(mockApiDao, mockPresenter, userDao);

        // Act
        GetCaloriesInputData inputData = new GetCaloriesInputData("Invalid Recipe", "testUser");
        interactor.execute(inputData);
    }
}