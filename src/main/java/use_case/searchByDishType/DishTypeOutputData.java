package use_case.searchByDishType;

import org.json.JSONArray;

import java.util.List;

public class DishTypeOutputData {
    private JSONArray recipe;
    private final boolean useCaseFailed;

    public DishTypeOutputData(JSONArray recipe, boolean useCaseFailed) {
        this.recipe = recipe;
        this.useCaseFailed = useCaseFailed;
    }

    public JSONArray getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

