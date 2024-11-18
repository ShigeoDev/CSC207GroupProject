package use_case.store_recipe;

/**
 * DAO for the Store Recipe Use Case.
 */
import java.util.ArrayList;

public interface StoreRecipeDataAccessInterface {

    ArrayList getRecipes(String username);
}
