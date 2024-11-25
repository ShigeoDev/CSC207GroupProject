package entity;

import java.util.List;
import java.util.Map;

/**
 * The Recipe Class.
 */
public class Recipe {
    private String name;
    private List<String> dishType;
    private List<String> mealType;
    private List<String> ingredients;
    private String url;
    private double calories;
    private Map<String, Double> nutrients;

    // Constructor
    public Recipe(String name, List<String> dishType, List<String> mealType, List<String> ingredients,
                  String url, double calories, Map<String, Double> nutrients) {
        this.name = name;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.url = url;
        this.calories = calories;
        this.nutrients = nutrients;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDishType() {
        return dishType;
    }

    public void setDishType(List<String> dishType) {
        this.dishType = dishType;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return url;
    }

    public void setInstructions(String url) {
        this.url = url;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Map<String, Double> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Map<String, Double> nutrients) {
        this.nutrients = nutrients;
    }

    // ToString method for easy representation
    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", dishType='" + dishType + '\'' +
                ", mealType='" + mealType + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + url + '\'' +
                ", calories=" + calories +
                ", nutrients=" + nutrients +
                '}';
    }
}
