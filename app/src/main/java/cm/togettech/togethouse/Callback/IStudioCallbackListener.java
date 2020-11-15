package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.StudioModel;

import java.util.List;

public interface IStudioCallbackListener {
    void onStudioLoadSuccess(List<StudioModel> studioModels);
    void onStudioLoadFailed(String message);
}
