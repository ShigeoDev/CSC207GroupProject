package data_access;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import use_case.store_recipe.StoreRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, StoreRecipeDataAccessInterface {

    private Map<String, String> account = new HashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private Map<String, ArrayList> recipes = new HashMap<>();
    private String currentUsername;


    public FileUserDataAccessObject(String filename, UserFactory userFactory) {
        try {
            final Path path = Paths.get("src/main/java/data_access/" + filename);
            final String jsonString = Files.readString(path.toAbsolutePath());

            final JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject user = jsonArray.getJSONObject(i);
                account.put(user.getString("username"), user.getString("password"));
                final JSONArray jsonrecipes = user.getJSONArray("recipes");
                final ArrayList<JSONObject> recipeArray = new ArrayList<>();
                for (int j = 0; j < jsonrecipes.length(); j++) {
                    final JSONObject recipe = jsonrecipes.getJSONObject(j);
                    recipeArray.add(recipe);
                final User name = userFactory.create(user.getString("username"), user.getString("password"));
                accounts.put(user.getString("username"), name);
                }
                recipes.put(user.getString("username"), recipeArray);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return account.containsKey(identifier);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

//    @Override
//    public ArrayList getRecipes(String username) {
//        return recipes.get(username);
//    }
}
