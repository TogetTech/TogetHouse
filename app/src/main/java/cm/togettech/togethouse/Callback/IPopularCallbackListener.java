package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.PopularCategoryModel;

import java.util.List;

public interface IPopularCallbackListener {

    void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModels);
    void onPopularLLoadFailed(String message);
}
