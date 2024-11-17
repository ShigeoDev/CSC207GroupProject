package use_case.Homepage;

import use_case.store_recipe.StoreRecipeOutputBoundary;

public interface HomepageOutputBoundary {

    public void prepareSuccessView();
    public void switchToStoreRecipeView();
}
