package entity;

import java.util.List;
import java.util.Map;

/**
 * The Recipe Class.
 */
public class Recipe {
    private String name;
    private String cuisineType;
    private String dishType;
    private String mealType;
    private List<String> ingredients;
    private String instructions;
    private int calories;
    private Map<String, Double> nutrients;

    // Constructor
    public Recipe(String name, String cuisineType, String dishType, String mealType, List<String> ingredients,
                  String instructions, int calories, Map<String, Double> nutrients) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.instructions = instructions;
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

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
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
                ", cuisineType='" + cuisineType + '\'' +
                ", dishType='" + dishType + '\'' +
                ", mealType='" + mealType + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", calories=" + calories +
                ", nutrients=" + nutrients +
                '}';
    }
}
