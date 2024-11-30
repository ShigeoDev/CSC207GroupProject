package use_case;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.NutritionFilterPage.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the `NutritionFilterPageInteractor` use case.
 * Ensures correct functionality for filtering recipes based on selected nutrients and switching views.
 */
public class NutritionFilterPageInteractorTest {

    // Main use case class for handling nutrition filter functionality
    private NutritionFilterPageInteractor interactor;
    // Interface for accessing recipe data
    private NutritionFilterPageDataAccessInterface dataAccess;
    // Mocked output boundary to capture outputs
    private MockNutritionFilterPageOutputBoundary outputBoundary;

    @BeforeEach
    public void setUp() {
        // Initialize dependencies and set up the NutritionFilterPageInteractor instance

        // Mock data access object to simulate data retrieval
        dataAccess = new MockNutritionFilterPageDataAccess();
        // Mock output boundary to capture outputs for verification
        outputBoundary = new MockNutritionFilterPageOutputBoundary();
        // Main interactor instance
        interactor = new NutritionFilterPageInteractor(dataAccess, outputBoundary);
    }

    @Test
    public void testExecuteWithValidSelectedNutrients() {
        // Test filtering recipes with valid selected nutrients

        // Set up test data
        ArrayList<String> selectedNutrients = new ArrayList<>(List.of("Vitamin C"));
        NutritionFilterPageInputData inputData = new NutritionFilterPageInputData(selectedNutrients);

        // Execute the interactor with input data
        JSONArray result = interactor.execute(inputData);

        // Verify that result is not null and contains expected recipes
        assertNotNull(result);
        assertEquals(1, result.length());
        assertEquals("Orange Juice", result.getJSONObject(0).getString("name"));

        // Verify that outputBoundary.prepareSuccessView was called
        assertNotNull(outputBoundary.successData);
        assertNull(outputBoundary.failMessage);
    }

    @Test
    public void testExecuteWithNoRecipesFound() {
        // Test handling when no recipes match the selected nutrients

        // Set up input data with nutrients that yield no recipes
        ArrayList<String> selectedNutrients = new ArrayList<>(List.of("Nonexistent Nutrient"));
        NutritionFilterPageInputData inputData = new NutritionFilterPageInputData(selectedNutrients);

        // Execute the interactor
        JSONArray result = interactor.execute(inputData);

        // Verify that result is null and appropriate fail view is prepared
        assertNull(result);
        assertNull(outputBoundary.successData);
        assertEquals("No recipes found matching the selected nutrients.", outputBoundary.failMessage);
    }

    @Test
    public void testExecuteWithException() {
        // Test handling when an exception occurs during data access

        // Set up input data that will cause an exception
        ArrayList<String> selectedNutrients = new ArrayList<>(List.of("Error"));
        NutritionFilterPageInputData inputData = new NutritionFilterPageInputData(selectedNutrients);

        // Execute the interactor
        JSONArray result = interactor.execute(inputData);

        // Verify that result is null and appropriate fail view is prepared
        assertNull(result);
        assertNull(outputBoundary.successData);
        assertTrue(outputBoundary.failMessage.contains("An error occurred while fetching recipes: Simulated data access exception"));
    }

    @Test
    public void testSwitchToHomepage() {
        // Test switching the view to the homepage

        // Username for testing
        String username = "testUser";

        // Call the method to switch to homepage
        interactor.switchToHomepage(username);

        // Verify that the output boundary prepared the homepage view with the correct username
        assertEquals(username, outputBoundary.preparedHomepageUsername);
    }

    @Test
    public void testSwitchToNutritionFilterPage() {
        // Test switching the view to the nutrition filter page

        // Username for testing
        String username = "testUser";

        // Call the method to switch to the nutrition filter page
        interactor.switchToNutritionFilterPage(username);

        // Verify that the output boundary prepared the nutrition filter page with the correct username
        assertEquals(username, outputBoundary.preparedNutritionFilterPageUsername);
    }

    /**
     * Mock implementation of the NutritionFilterPageDataAccessInterface for testing purposes.
     * Simulates data retrieval based on selected nutrients.
     */
    private class MockNutritionFilterPageDataAccess implements NutritionFilterPageDataAccessInterface {
        @Override
        public JSONArray getRecipesByNutrients(ArrayList<String> selectedNutrients) {
            if (selectedNutrients == null) {
                throw new IllegalArgumentException("selectedNutrients cannot be null");
            }
            if (selectedNutrients.contains("Error")) {
                throw new RuntimeException("Simulated data access exception");
            }
            if (selectedNutrients.contains("Vitamin C")) {
                // Return a JSONArray containing recipes with Vitamin C
                JSONArray recipes = new JSONArray();
                JSONObject recipe1 = new JSONObject();
                recipe1.put("name", "Orange Juice");
                recipe1.put("nutrients", new JSONArray(List.of("Vitamin C")));
                recipes.put(recipe1);
                return recipes;
            } else {
                // Return empty array if no matching recipes found
                return new JSONArray();
            }
        }
    }

    /**
     * Mock implementation of the NutritionFilterPageOutputBoundary for testing purposes.
     * Captures outputs for verification.
     */
    private class MockNutritionFilterPageOutputBoundary implements NutritionFilterPageOutputBoundary {

        public String failMessage = null;
        public NutritionFilterPageOutputData successData = null;
        public String preparedHomepageUsername = null;
        public String preparedNutritionFilterPageUsername = null;

        @Override
        public void prepareSuccessView(NutritionFilterPageOutputData outputData) {
            successData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            failMessage = errorMessage;
        }

        @Override
        public void prepareHomepage(String username) {
            preparedHomepageUsername = username;
        }

        @Override
        public void prepareNutritionFilterPage(String username) {
            preparedNutritionFilterPageUsername = username;
        }
    }
}
