package use_case.NutritionFilterPage;

/**
 * The output boundary for the Filter Based on Nutrition Use Case.
 */
public interface NutritionFilterPageOutputBoundary {
    /**
     * Prepares the success view for the Filter Based on Nutrition Use Case.
     * @param nutritionFilterPageOutputData the output data
     */
    void prepareSuccessView(NutritionFilterPageOutputData nutritionFilterPageOutputData);

    /**
     * Prepares the failure view for the Filter Based on Nutrition Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the NutritionFilter view, typically used for displaying or transitioning to
     * the user interface related to nutrition filter.
     */
    void prepareNutritionFilterPage(String username);

    void prepareHomepage(String username);
}
