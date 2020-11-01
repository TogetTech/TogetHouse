package cm.togettech.togethouse.Callback;

import cm.togettech.togethouse.Model.AppartementModels;

import java.util.List;

public interface IAppartementCallbackListener {

    void onAppartementLoadSuccess(List<AppartementModels> appartementModels);
    void onAppartementLoadFailed(String message);
}
