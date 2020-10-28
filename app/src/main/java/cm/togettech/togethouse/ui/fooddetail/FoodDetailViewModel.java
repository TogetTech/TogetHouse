package cm.togettech.togethouse.ui.fooddetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.CommentModel;
import cm.togettech.togethouse.Model.FoodModel;


public class FoodDetailViewModel extends ViewModel {

    private MutableLiveData<FoodModel> mutableLiveDataFood;
    private MutableLiveData<CommentModel> modelMutableDataComment;

    public void setCommentModel(CommentModel commentModel){
        if (modelMutableDataComment != null)
            modelMutableDataComment.setValue(commentModel);
    }

    public MutableLiveData<CommentModel> getModelMutableDataComment(){
        return modelMutableDataComment;
    }

    public FoodDetailViewModel() {
        modelMutableDataComment = new MutableLiveData<>();
    }


    public MutableLiveData<FoodModel> getMutableLiveDataFood() {
        if (mutableLiveDataFood == null)
            mutableLiveDataFood = new MutableLiveData<>();
        mutableLiveDataFood.setValue(Common.selectedFood);
        return mutableLiveDataFood;
    }

    public void setFoodModel(FoodModel foodModel) {
        if (mutableLiveDataFood !=null)
                mutableLiveDataFood.setValue(foodModel);
    }
}