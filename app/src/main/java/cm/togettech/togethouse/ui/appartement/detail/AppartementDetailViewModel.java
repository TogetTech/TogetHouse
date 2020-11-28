package cm.togettech.togethouse.ui.appartement.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cm.togettech.togethouse.Callback.IAppartementCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.AppartementModel;

public class AppartementDetailViewModel extends ViewModel implements IAppartementCallbackListener {
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

    private MutableLiveData<List<AppartementModel>> appartementList;
    private MutableLiveData<String> messageError;
    private IAppartementCallbackListener appartementCallbackListener;

    public AppartementDetailViewModel() {
        appartementCallbackListener = this;
    }

    public MutableLiveData<List<AppartementModel>> getAppartementList() {
        if (appartementList == null){
            appartementList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadAppartementList();
        }
        return appartementList;
    }
    private void loadAppartementList() {
        List<AppartementModel>  tempList = new ArrayList<>();
        DatabaseReference appartementRef = FirebaseDatabase.getInstance().getReference(Common.APPARTEMENT_REF);
        appartementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    AppartementModel appartementModel = itemSnapshot.getValue(AppartementModel.class);
                    tempList.add(appartementModel);
                }
                appartementCallbackListener.onAppartementLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                appartementCallbackListener.onAppartementLoadFailed(databaseError.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onAppartementLoadSuccess(List<AppartementModel> appartementModels) {
        appartementList.setValue(appartementModels);
    }

    @Override
    public void onAppartementLoadFailed(String message) {
        messageError.setValue(message);
    }
}
