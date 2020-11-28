package cm.togettech.togethouse.ui.maison;

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

import cm.togettech.togethouse.Callback.IMaisonCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.MaisonModel;

public class MaisonViewModel extends ViewModel implements IMaisonCallbackListener {

    private MutableLiveData<List<MaisonModel>> maisonList;
    private MutableLiveData<String> messageError;
    private IMaisonCallbackListener maisonCallbackListener;


    public MaisonViewModel() {
        maisonCallbackListener = this;
    }

    public MutableLiveData<List<MaisonModel>> getMaisonList() {
        if (maisonList == null){
            maisonList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadMaisonList();
        }
        return maisonList;
    }

    private void loadMaisonList() {
        List<MaisonModel>  tempList = new ArrayList<>();
        DatabaseReference maisonRef = FirebaseDatabase.getInstance().getReference(Common.MAISON_REF);
        maisonRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    MaisonModel maisonModel = itemSnapshot.getValue(MaisonModel.class);
                    tempList.add(maisonModel);
                }
                maisonCallbackListener.onMaisonLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                maisonCallbackListener.onMaisonLoadFailed(databaseError.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onMaisonLoadSuccess(List<MaisonModel> maisonModels) {
        maisonList.setValue(maisonModels);
    }

    @Override
    public void onMaisonLoadFailed(String message) {
        messageError.setValue(message);
    }
}