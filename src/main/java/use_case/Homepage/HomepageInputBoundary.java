package use_case.Homepage;

public interface HomepageInputBoundary {

    public void savedRecipe(HomepageInputData homepageInputData);
    public void mealPlan(String username);
    void switchToDishType();
    public void getCalories(String username);

    void execute();
}
