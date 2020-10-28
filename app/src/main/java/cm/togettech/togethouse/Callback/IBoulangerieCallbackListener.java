package cm.togettech.togethouse.Callback;


import cm.togettech.togethouse.Model.BoulangerieModel;

import java.util.List;

public interface IBoulangerieCallbackListener {
    void onBoulangerieLoadSuccess(List<BoulangerieModel> boulangerieModelList);
    void onBoulangerieLoadFailed(String message);
}
