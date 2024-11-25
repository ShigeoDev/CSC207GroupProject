package data_access;

import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.NutritionFilterPage.NutritionFilterPageDataAccessInterface;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ApiDataAccessObject implements NutritionFilterPageDataAccessInterface  {
    public final String Url = "https://api.edamam.com";
    public final String Key = System.getenv("Key");
    public final String Id = System.getenv("Id");
    private static final String MESSAGE = "message";
    private static final Map<String, String> NUTRIENT_NAME_TO_ID = new HashMap<>();

    static {
        NUTRIENT_NAME_TO_ID.put("Vitamin A", "VITA_RAE");
        NUTRIENT_NAME_TO_ID.put("Vitamin C", "VITC");
        NUTRIENT_NAME_TO_ID.put("Vitamin D", "VITD");
        NUTRIENT_NAME_TO_ID.put("Vitamin E", "TOCPHA");
        NUTRIENT_NAME_TO_ID.put("Vitamin K", "VITK1");
        NUTRIENT_NAME_TO_ID.put("Carbohydrates", "CHOCDF");
        NUTRIENT_NAME_TO_ID.put("Fat", "FAT");
        NUTRIENT_NAME_TO_ID.put("Sugar", "SUGAR");
        NUTRIENT_NAME_TO_ID.put("Fiber", "FIBTG");
        NUTRIENT_NAME_TO_ID.put("Protein", "PROCNT");
        NUTRIENT_NAME_TO_ID.put("Iron", "FE");
        NUTRIENT_NAME_TO_ID.put("Calcium", "CA");
        NUTRIENT_NAME_TO_ID.put("Potassium", "K");
        NUTRIENT_NAME_TO_ID.put("Sodium", "NA");
        NUTRIENT_NAME_TO_ID.put("Magnesium", "MG");
        NUTRIENT_NAME_TO_ID.put("Phosphorus", "P");
    }

    public JSONObject getRecipebyName(String recipeName) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/api/recipes/v2?type=public&q=%s&app_id=%s&app_key=%s", Url, recipeName, Id, Key))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            assert response.body() != null;
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody;
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
            assert response.body() != null;
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
    public List<Recipe> getRecipesByNutrients(List<String> selectedNutrients) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        String baseUrl = String.format("%s/api/recipes/v2?type=public&app_id=%s&app_key=%s", Url, Id, Key);

        StringBuilder nutrientParams = new StringBuilder();
        for (String selectNutrient : selectedNutrients) {
            String nutrientId = NUTRIENT_NAME_TO_ID.get(selectNutrient);
            if (nutrientId != null) {
                String paramName = String.format("nutrients[%s]", nutrientId);
                String paramValue = "gte 10";

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
            assert response.body() != null;
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return parseRecipes(responseBody);
            } else {
                throw new RuntimeException(responseBody.optString(MESSAGE, "Unknown error occurred."));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Recipe> parseRecipes(JSONObject json) throws JSONException {
        List<Recipe> recipes = new ArrayList<>();
        JSONArray hits = json.getJSONArray("hits");
        for (int i = 0; i < hits.length(); i++) {
            JSONObject hit = hits.getJSONObject(i);
            JSONObject recipeJson = hit.getJSONObject("recipe");
            Recipe recipe = parseRecipe(recipeJson);
            recipes.add(recipe);
        }
        return recipes;
    }

    private Recipe parseRecipe(JSONObject json) throws JSONException {
        String name = json.optString("label");
        List<String> dishType = jsonArrayToList(json.optJSONArray("dishType"));
        List<String> mealType = jsonArrayToList(json.optJSONArray("mealType"));
        List<String> ingredients = jsonArrayToList(json.optJSONArray("ingredientLines"));
        String url = json.optString("url");
        double calories = json.optDouble("calories");
        Map<String, Double> nutrients = parseNutrients(json.optJSONObject("totalNutrients"));

        return new Recipe(name, dishType, mealType, ingredients, url, calories, nutrients);
    }

    // Helper Method: Convert JSONArray to List<String>
    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.optString(i));
            }
        }
        return list;
    }

    // Helper Method
    private Map<String, Double> parseNutrients(JSONObject nutrientsJson) throws JSONException {
        Map<String, Double> nutrients = new HashMap<>();
        if (nutrientsJson != null) {
            Iterator<String> keys = nutrientsJson.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject nutrientInfo = nutrientsJson.getJSONObject(key);
                double quantity = nutrientInfo.optDouble("quantity");
                nutrients.put(key, quantity);
            }
        }
        return nutrients;
    }
}
