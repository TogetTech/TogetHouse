package cm.togettech.togethouse.ui.restaurant.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.CommentModel;
import cm.togettech.togethouse.Model.RestaurantModel;

public class RestaurantDetailViewModel extends ViewModel{

    private MutableLiveData<RestaurantModel> mutableLiveDataRestaurantList;
    private MutableLiveData<CommentModel> modelMutableDataComment;


    public void setCommentModel(CommentModel commentModel){
        if (modelMutableDataComment != null)
            modelMutableDataComment.setValue(commentModel);
    }

    public MutableLiveData<CommentModel> getModelMutableDataComment(){
        return modelMutableDataComment;
    }
    public RestaurantDetailViewModel (){
        modelMutableDataComment = new MutableLiveData<>();

    }
    public MutableLiveData<RestaurantModel> getMutableLiveDataRestaurant() {
        if (mutableLiveDataRestaurantList == null)
            mutableLiveDataRestaurantList = new MutableLiveData<>();
        mutableLiveDataRestaurantList.setValue(Common.restaurantSelected);
        return mutableLiveDataRestaurantList;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        if (mutableLiveDataRestaurantList !=null)
                mutableLiveDataRestaurantList.setValue(restaurantModel);
    }


}
