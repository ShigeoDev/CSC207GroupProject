package use_case.searchByDishType;

import java.util.List;

public class DishTypeOutputData {
    private List<String> recipe;
    private final boolean useCaseFailed;

    public DishTypeOutputData(List<String> recipe, boolean useCaseFailed) {
        this.recipe = recipe;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

