package cm.togettech.togethouse.ui.chambre.detail;

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

import cm.togettech.togethouse.Callback.IChambreCallbackListener;
import cm.togettech.togethouse.Callback.ITerrainCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.Model.TerrainModel;

public class ChambreDetailViewModel extends ViewModel implements IChambreCallbackListener{

    private MutableLiveData<ChambreModel> mutableLiveDataChambreList;

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


    private MutableLiveData<List<ChambreModel>> chambreList;
    private MutableLiveData<String> messageError;
    private IChambreCallbackListener chambreCallbackListener;

    public ChambreDetailViewModel() {
        chambreCallbackListener = this;
    }

    public MutableLiveData<List<ChambreModel>> getChambreList() {
        if (chambreList == null){
            chambreList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadChambreList();
        }
        return chambreList;
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
                chambreCallbackListener.onChambreLoadFailed(databaseError.getMessage());
            }
        });
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
