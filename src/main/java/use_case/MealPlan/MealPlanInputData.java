package use_case.MealPlan;

/**
 * The Input Data for the Signup Use Case.
 */
public class MealPlanInputData {
    private String username;

    public MealPlanInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
