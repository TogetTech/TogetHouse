package cm.togettech.togethouse.Callback;

import java.util.List;
import cm.togettech.togethouse.Model.MaisonModel;

public interface IMaisonCallbackListener {

    void onMaisonLoadSuccess(List<MaisonModel> maisonModels);
    void onMaisonLoadFailed(String message);
}
