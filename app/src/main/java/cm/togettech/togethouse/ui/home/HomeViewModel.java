package cm.togettech.togethouse.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import cm.togettech.togethouse.Callback.IStudioCallbackListener;
import cm.togettech.togethouse.Callback.IAppartementCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.BestDealModel;
import cm.togettech.togethouse.Model.AppartementModels;
import cm.togettech.togethouse.Model.StudioModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements IAppartementCallbackListener, IStudioCallbackListener {

    private MutableLiveData<List<AppartementModels>> appartementList;
    private MutableLiveData<List<StudioModel>> studioList;
    private MutableLiveData<String> messageError;

    private IAppartementCallbackListener appartementCallbackListener;
    private IStudioCallbackListener studioCallbackListener;

    public HomeViewModel() {
        appartementCallbackListener = this;
        studioCallbackListener = this;
    }

    //Appartement
    public MutableLiveData<List<AppartementModels>> getAppartementList() {
        if (appartementList == null){
            appartementList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadAppartementList();
        }
        return  appartementList;
    }

    //Studio
    public MutableLiveData<List<StudioModel>> getStudioList() {
        if (studioList == null){
            studioList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadStudioList();
        }
        return studioList;
    }

    // Chargement des appartements
    private void loadAppartementList() {
        List<AppartementModels>  tempList = new ArrayList<>();
        DatabaseReference appartementRef = FirebaseDatabase.getInstance().getReference(Common.APPARTEMENT_REF);
        appartementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    AppartementModels model = itemSnapshot.getValue(AppartementModels.class);
                    tempList.add(model);
                }
                appartementCallbackListener.onAppartementLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                appartementCallbackListener.onAppartementLoadFailed(databaseError.getMessage());
            }
        });
    }

    // Chargement des studios
    private void loadStudioList() {
        List<StudioModel>  tempList = new ArrayList<>();
        DatabaseReference studioRef = FirebaseDatabase.getInstance().getReference(Common.STUDIO_REF);
        studioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    StudioModel model = itemSnapshot.getValue(StudioModel.class);
                    tempList.add(model);
                }
                studioCallbackListener.onStudioLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                studioCallbackListener.onStudioLoadFailed(databaseError.getMessage());
            }
        });
    }



    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    //Exceptions appartement
    @Override
    public void onAppartementLoadSuccess(List<AppartementModels> appartementModels) {
        appartementList.setValue(appartementModels);
    }
    @Override
    public void onAppartementLoadFailed(String message) {
        messageError.setValue(message);

    }

    //Exceptions studio
    @Override
    public void onStudioLoadSuccess(List<StudioModel> studioModels) {
        studioList.setValue(studioModels);
    }
    @Override
    public void onStudioLoadFailed(String message) {
        messageError.setValue(message);
    }
}