package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.RestaurantModel;

public class RestaurantItemClick {
    private boolean success;
    private RestaurantModel restaurantModel;

    public RestaurantItemClick(boolean success, RestaurantModel restaurantModel) {
        this.success = success;
        this.restaurantModel = restaurantModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }
}
