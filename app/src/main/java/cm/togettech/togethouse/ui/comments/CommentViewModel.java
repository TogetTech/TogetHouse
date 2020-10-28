package cm.togettech.togethouse.ui.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cm.togettech.togethouse.Model.CommentModel;

import java.util.List;

public class  CommentViewModel extends ViewModel {

    private MutableLiveData<List<CommentModel>> mutableLiveDataFoodList;

    public CommentViewModel() {
        mutableLiveDataFoodList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CommentModel>> getMutableLiveDataCommentList(){
        return mutableLiveDataFoodList;
    }

    public void setCommentList(List<CommentModel> commentList){
        mutableLiveDataFoodList.setValue(commentList);
    }
}
