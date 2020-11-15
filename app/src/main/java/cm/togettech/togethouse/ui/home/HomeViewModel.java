package cm.togettech.togethouse.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cm.togettech.togethouse.Callback.IChambreCallbackListener;
import cm.togettech.togethouse.Callback.IStudioCallbackListener;
import cm.togettech.togethouse.Callback.IAppartementCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.AppartementModel;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.Model.StudioModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements
        IAppartementCallbackListener, IStudioCallbackListener, IChambreCallbackListener {

    private MutableLiveData<List<AppartementModel>> appartementList;
    private MutableLiveData<List<StudioModel>> studioList;
    private MutableLiveData<List<ChambreModel>> chambreList;
    private MutableLiveData<String> messageError;

    private IAppartementCallbackListener appartementCallbackListener;
    private IStudioCallbackListener studioCallbackListener;
    private IChambreCallbackListener chambreCallbackListener;

    public HomeViewModel() {
        appartementCallbackListener = this;
        studioCallbackListener = this;
        chambreCallbackListener = this;
    }

    //Appartement
    public MutableLiveData<List<AppartementModel>> getAppartementList() {
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

    //Chambre
    public MutableLiveData<List<ChambreModel>> getChambreList() {
        if (chambreList == null){
            chambreList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadChambreList();
        }
        return chambreList;
    }

    // Chargement des appartements
    private void loadAppartementList() {
        List<AppartementModel>  tempList = new ArrayList<>();
        DatabaseReference appartementRef = FirebaseDatabase.getInstance().getReference(Common.APPARTEMENT_REF);
        appartementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    AppartementModel model = itemSnapshot.getValue(AppartementModel.class);
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

    // Chargement des chambres
    private void loadChambreList() {
        List<ChambreModel>  tempList = new ArrayList<>();
        DatabaseReference chambreRef = FirebaseDatabase.getInstance().getReference(Common.CHAMBRE_REF);
        chambreRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    ChambreModel model = itemSnapshot.getValue(ChambreModel.class);
                    tempList.add(model);
                }
                chambreCallbackListener.onChambreLoadSuccess(tempList);
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
    public void onAppartementLoadSuccess(List<AppartementModel> appartementModels) {
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


    //Exceptions chambre
    @Override
    public void onChambreLoadSuccess(List<ChambreModel> chambreModels) {
        chambreList.setValue(chambreModels);
    }
    @Override
    public void onChambreLoadFailed(String message) {
        messageError.setValue(message);
    }
}