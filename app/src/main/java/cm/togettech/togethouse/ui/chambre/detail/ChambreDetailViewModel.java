package cm.togettech.togethouse.ui.chambre.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.ChambreModel;

public class ChambreDetailViewModel extends ViewModel {

    private MutableLiveData<ChambreModel> mutableLiveDataChambreList;
    //Suite commentaire


    public ChambreDetailViewModel() {
    }

    public MutableLiveData<ChambreModel> getMutableLiveDataChambre() {
        if (mutableLiveDataChambreList == null)
            mutableLiveDataChambreList = new MutableLiveData<>();
        mutableLiveDataChambreList.setValue(Common.selectedChambre);
        return mutableLiveDataChambreList;
    }

    public void setChambreModel(ChambreModel chambreModel) {
        if (mutableLiveDataChambreList !=null)
            mutableLiveDataChambreList.setValue(chambreModel);
    }
}
