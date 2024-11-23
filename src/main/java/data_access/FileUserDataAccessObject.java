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
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import use_case.store_recipe.StoreRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, StoreRecipeDataAccessInterface {

    private final File file;
    private final Map<String, User> accounts = new HashMap<>();
    private Map<String, ArrayList> recipes = new HashMap<>();
    private String currentUsername;

    public FileUserDataAccessObject(String filename, UserFactory userFactory) {
        file = new File(filename);
        if (file.length() == 0) {
            save();
        }
        else {
            try {
                final Path path = Paths.get("src/main/java/data_access/" + filename);
                final String jsonString = Files.readString(path.toAbsolutePath());

                final JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject userJSON = jsonArray.getJSONObject(i);

                    String username = userJSON.getString("username");
                    String password = userJSON.getString("password");
                    User user = userFactory.create(username, password);
                    accounts.put(username, user);

                    final JSONArray jsonrecipes = userJSON.getJSONArray("recipes");
                    System.out.println(jsonrecipes.toList());
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
    }


    private void save() {
        Path path = Paths.get("src/main/java/data_access/" + file.getName());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            JSONArray jsonArray = new JSONArray();
            for (Map.Entry<String, User> entry : accounts.entrySet()) {
                String username = entry.getKey();
                User user = entry.getValue();

                JSONObject userJSON = new JSONObject();
                userJSON.put("username", username);
                userJSON.put("password", user.getPassword());
                accounts.put(username, user);

                JSONArray userRecipes = new JSONArray();
                userJSON.put("recipes", userRecipes);
                jsonArray.put(userJSON);
                recipes.put(username, new ArrayList<>());
            }
            writer.write(jsonArray.toString(2));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
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

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public ArrayList getRecipes(String username) {
        return recipes.get(username);
    }

//    @Override
//    public ArrayList getRecipes(String username) {
//        return recipes.get(username);
//    }
}
