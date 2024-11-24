package interface_adapter.DishType;

public class DishTypeState {
    private String DishType = null;
    private String error;

    public String getDishType() {
        return DishType;
    }

    public void setCurrentDishType(String currentDishType) {
        this.DishType = currentDishType;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}