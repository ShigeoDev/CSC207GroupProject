package data_access;

import entity.User;
import org.json.JSONObject;
import use_case.MealPlan.MealPlanDataAccessInterface;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import use_case.store_recipe.StoreRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, StoreRecipeDataAccessInterface, MealPlanDataAccessInterface {

    private final List<User> users = new ArrayList<User>();
    private final Map<User, ArrayList<JSONObject>> recipes = new HashMap<>();
    private JSONObject[] meals = new JSONObject[3];

    private String currentUsername;

    public boolean existsByName(String identifier) {
        for (User user : users) {
            if (user.getName().equals(identifier)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByName(User user) {
        if (users.contains(user)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
        recipes.put(user, new ArrayList<JSONObject>());
    }

    @Override
    public User get(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void saveRecipe(JSONObject recipe, String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                recipes.get(user).add(recipe);
            }
        }
    }

    @Override
    public ArrayList getRecipes(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return recipes.get(user);
            }
        }
        return null;
    }

    @Override
    public JSONObject getRecipebyMeal(String mealName) {
        if (mealName.equals("breakfast")) {
            return meals[0];
        }
        else if (mealName.equals("lunch")) {
            return meals[1];
        }
        else if (mealName.equals("dinner")) {
            return meals[2];
        }
        return null;
    }

    public void setRecipebyMeal(JSONObject recipe, String mealName) {
        if (mealName.equals("breakfast")) {
            this.meals[0] = recipe;
        }
        else if (mealName.equals("lunch")) {
            this.meals[1] = recipe;
        }
        else if (mealName.equals("dinner")) {
            this.meals[2] = recipe;
        }
    }
}
