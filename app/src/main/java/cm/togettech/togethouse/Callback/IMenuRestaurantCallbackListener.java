package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.RestaurantModel;

import java.util.List;

public interface IMenuRestaurantCallbackListener {
    void onMenuRestaurantLoadSuccess(List<RestaurantModel> menuRestaurantModels);
    void onMenuRestaurantLoadFailed(String message);
}
