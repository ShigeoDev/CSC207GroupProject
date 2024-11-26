package use_case.searchByDishType;

public interface DishTypeOutputBoundary {
    /**
     * Prepares the success view for the Note related Use Cases.
     * @param outputData the output data
     */
    void prepareSuccessView(DishTypeOutputData outputData);

    /**
     * Prepares the failure view for the Note related Use Cases.
     * @param  outputData the explanation of the failure
     */
    void prepareFailView(DishTypeOutputData outputData);
}

