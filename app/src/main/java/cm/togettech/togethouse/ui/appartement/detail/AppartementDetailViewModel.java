package cm.togettech.togethouse.ui.appartement.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.AppartementModel;

public class AppartementDetailViewModel extends ViewModel {
    private MutableLiveData<AppartementModel> mutableLiveDataAppartementList;

    public MutableLiveData<AppartementModel> getMutableLiveDataAppartement() {
        if (mutableLiveDataAppartementList == null)
            mutableLiveDataAppartementList = new MutableLiveData<>();
        mutableLiveDataAppartementList.setValue(Common.selectedAppartement);
        return mutableLiveDataAppartementList;
    }

    public void setAppartementModel(AppartementModel appartementModel) {
        if (mutableLiveDataAppartementList !=null)
            mutableLiveDataAppartementList.setValue(appartementModel);
    }
}
