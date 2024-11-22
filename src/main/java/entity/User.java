package entity;

import java.util.ArrayList;
import java.util.List;


/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private final List<String> savedRecipes;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.savedRecipes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getSavedRecipes() {
        return savedRecipes;
    }

}
