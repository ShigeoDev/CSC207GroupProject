package data_access;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.GetCalories.GetCaloriesDataAccessInterface;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.MealPlan.MealPlanDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import use_case.store_recipe.StoreRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, StoreRecipeDataAccessInterface, GetCaloriesDataAccessInterface {

    private final File file;
    private final ArrayList<User> accounts = new ArrayList<>();
    private Map<String, ArrayList<JSONObject>> recipes = new HashMap<>();

    private String currentUsername;

    public FileUserDataAccessObject(String filename, UserFactory userFactory) {
        file = new File(filename);
        final Path path = Paths.get("src/main/java/data_access/" + filename);
        try {
            final String jsonString = Files.readString(path.toAbsolutePath());

            final JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject userJSON = jsonArray.getJSONObject(i);

                String username = userJSON.getString("username");
                String password = userJSON.getString("password");
                User user = userFactory.create(username, password);
                accounts.add(user);

                final JSONArray jsonrecipes = userJSON.getJSONArray("recipes");
                final ArrayList<JSONObject> recipeArray = new ArrayList<>();
                for (int j = 0; j < jsonrecipes.length(); j++) {
                    final JSONObject recipe = jsonrecipes.getJSONObject(j);
                    recipeArray.add(recipe);
                }
                recipes.put(username, recipeArray);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save() {
        Path path = Paths.get("src/main/java/data_access/" + file.getName());
        JSONArray jsonArray;

        if (Files.exists(path) && file.length() > 0) {
            try {
                String jsonString = Files.readString(path.toAbsolutePath());
                jsonArray = new JSONArray(jsonString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            jsonArray = new JSONArray();
        }

        for (int n = 0; n < accounts.size(); n++) {
            String username = accounts.get(n).getName();
            boolean userExists = false;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject existingUser = jsonArray.getJSONObject(i);
                if (existingUser.getString("username").equals(username)) {
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {
                JSONObject userJSON = new JSONObject();
                userJSON.put("username", username);
                userJSON.put("password", accounts.get(n).getPassword());
                accounts.add(accounts.get(n));

                JSONArray userRecipes = new JSONArray();
                userJSON.put("recipes", userRecipes);
                jsonArray.put(userJSON);
                recipes.put(username, new ArrayList<>());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write(jsonArray.toString(2));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        accounts.add(user);
        this.save();
    }

    @Override
    public User get(String user) {
        for (User account : accounts) {
            if (account.getName().equals(user)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public boolean existsByName(User user) {
        for (User account : accounts) {
            if (account == user) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        for (User account : accounts) {
            if (account.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList getRecipes(String username) {
        return recipes.get(username);
    }

    @Override
    public void saveRecipe(JSONObject recipe, String username) {
        boolean in = false;
        for (int i = 0; i < recipes.get(username).size(); i++) {
                if (recipes.get(username).get(i).getString("label").equals(recipe.getString("label"))) {
                    in = true;
                }
        }
        if (!in) {
            recipes.get(username).add(recipe);

            try {
                final Path path = Paths.get("src/main/java/data_access/" + file.getName());
                final String jsonString = Files.readString(path.toAbsolutePath());

                final JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject userJSON = jsonArray.getJSONObject(i);

                    if (username.equals(userJSON.getString("username"))) {
                        final JSONArray jsonrecipes = userJSON.getJSONArray("recipes");
                        jsonrecipes.put(recipe);
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                            writer.write(jsonArray.toString(2));
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

