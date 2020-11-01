package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.StudioModel;

import java.util.List;

public interface ICategoryCallbackListener {
    void onCategoryLoadSuccess(List<StudioModel> categoryModelList);
    void onCategoryLoadFailed(String message);
}
