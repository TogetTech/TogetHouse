package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.AppartementModel;

import java.util.List;

public interface IAppartementCallbackListener {

    void onAppartementLoadSuccess(List<AppartementModel> appartementModels);
    void onAppartementLoadFailed(String message);
}
