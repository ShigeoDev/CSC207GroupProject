package use_case.searchByDishType;

public class DishTypeOutputData {
    private String recipe;
    private final boolean useCaseFailed;

    public DishTypeOutputData(String recipe, boolean useCaseFailed) {
        this.recipe = recipe;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

