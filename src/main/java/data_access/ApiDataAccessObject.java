package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.MealPlan.MealPlanDataAccessInterface;
import use_case.NutritionFilterPage.NutritionFilterPageDataAccessInterface;
import use_case.searchByDishType.DishTypeUserDataAccessInterface;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiDataAccessObject implements DishTypeUserDataAccessInterface, MealPlanDataAccessInterface,
        NutritionFilterPageDataAccessInterface {
    public final String Url = "https://api.edamam.com";
    public final String Key = System.getenv("Key");
    public final String Id = System.getenv("Id");
    private static final String MESSAGE = "message";

    public JSONArray getRecipebyName(String recipeName) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/api/recipes/v2?type=public&q=%s&app_id=%s&app_key=%s", Url, recipeName, Id, Key))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody.getJSONArray("hits");
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }

        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getRecipebyMeal(String mealName) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/api/recipes/v2?type=public&app_id=%s&app_key=%s&mealType=%s&random=True", Url, Id, Key, mealName))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody.getJSONArray("hits")
                        .getJSONObject(0).getJSONObject("recipe");
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }

        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONArray getRecipeByDishType(String dishType) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/api/recipes/v2?type=public&app_id=%s&app_key=%s&dishType=%s&random=True", Url, Id, Key, dishType))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                return responseBody.getJSONArray("hits");
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of recipes from the Edamam API that are high in the specified nutrients.
     * @param selectedNutrients a list of nutrient names selected by the user for filtering recipes
     * @return a {@code JSONArray} containing the recipes that match the nutrient criteria
     */
    @Override
    public JSONArray getRecipesByNutrients(ArrayList<String> selectedNutrients) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        String baseUrl = String.format("%s/api/recipes/v2?type=public&app_id=%s&app_key=%s&random=true", Url, Id, Key);

        // Map of nutrient names to their IDs
        Map<String, String> nutrientNameToId = getNutrientNameToId();

        StringBuilder nutrientParams = new StringBuilder();
        for (String nutrientName : selectedNutrients) {
            String nutrientId = nutrientNameToId.get(nutrientName);
            if (nutrientId != null) {
                String paramName = String.format("nutrients[%s]", nutrientId);
                String paramValue = "1+";

                String encodedParamName = URLEncoder.encode(paramName, StandardCharsets.UTF_8);
                String encodedParamValue = URLEncoder.encode(paramValue, StandardCharsets.UTF_8);
                nutrientParams.append("&").append(encodedParamName).append("=").append(encodedParamValue);
            }
        }

        String urlWithParams = baseUrl + nutrientParams;

        final Request request = new Request.Builder()
                .url(urlWithParams)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("API request failed with code: " + response.code() +
                        " and message: " + response.message());
            }

            String responseBodyString = response.body().string();

            final JSONObject responseBody = new JSONObject(responseBodyString);
            return responseBody.optJSONArray("hits");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during the API request: " + e.getMessage(), e);
        }
    }

    //Helper method.
    /**
     * Creates and returns a mapping between nutrient names and their corresponding nutrient IDs used by the Edamam API.
     * @return a {@code Map<String, String>} containing nutrient names as keys and their API nutrient IDs as values
     */
    @NotNull
    private static Map<String, String> getNutrientNameToId() {
        Map<String, String> nutrientNameToId = new HashMap<>();
        nutrientNameToId.put("Vitamin A", "VITA_RAE");
        nutrientNameToId.put("Vitamin C", "VITC");
        nutrientNameToId.put("Vitamin B1", "THIA");
        nutrientNameToId.put("Vitamin B2", "RIBF");
        nutrientNameToId.put("Vitamin B3", "NIA");
        nutrientNameToId.put("Vitamin B6", "VITB6A");
        nutrientNameToId.put("Vitamin B12", "VITB12");
        nutrientNameToId.put("Vitamin D", "VITD");
        nutrientNameToId.put("Vitamin E", "TOCPHA");
        nutrientNameToId.put("Vitamin K", "VITK1");
        nutrientNameToId.put("CARBOHYDRATES", "CHOCDF");
        nutrientNameToId.put("FAT", "FAT");
        nutrientNameToId.put("SUGAR", "SUGAR");
        nutrientNameToId.put("FIBER", "FIBTG");
        nutrientNameToId.put("PROTEIN", "PROCNT");
        nutrientNameToId.put("IRON", "FE");
        nutrientNameToId.put("CALCIUM", "CA");
        nutrientNameToId.put("POTASSIUM", "K");
        nutrientNameToId.put("SODIUM", "NA");
        nutrientNameToId.put("MAGNESIUM", "MG");
        nutrientNameToId.put("PHOSPHORUS", "P");
        return nutrientNameToId;
    }

}
