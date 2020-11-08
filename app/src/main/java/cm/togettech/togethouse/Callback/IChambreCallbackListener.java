package cm.togettech.togethouse.Callback;

import java.util.List;

import cm.togettech.togethouse.Model.ChambreModel;

public interface IChambreCallbackListener {
    void onChambreLoadSuccess(List<ChambreModel> chambreModels);
    void onChambreLoadFailed(String message);
}