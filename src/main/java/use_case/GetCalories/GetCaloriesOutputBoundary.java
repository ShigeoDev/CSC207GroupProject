package use_case.GetCalories;

public interface GetCaloriesOutputBoundary {
    void prepareSuccessView(GetCaloriesOutputData getCaloriesOutputData);
    void prepareFailView(String error);
    void prepareHomeView();
}