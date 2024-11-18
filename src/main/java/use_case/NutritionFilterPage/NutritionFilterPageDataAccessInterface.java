package use_case.NutritionFilterPage;

import entity.Recipe;

import java.util.List;

/**
 * The interface of the DAO for the Filter Based on Nutrition Use Case.
 */
public interface NutritionFilterPageDataAccessInterface {

    List<Recipe> filterRecipes(NutritionFilterPageInputData inputData);
}
