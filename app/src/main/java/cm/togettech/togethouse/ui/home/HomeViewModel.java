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
import cm.togettech.togethouse.Callback.IMaisonCallbackListener;
import cm.togettech.togethouse.Callback.IStudioCallbackListener;
import cm.togettech.togethouse.Callback.IAppartementCallbackListener;
import cm.togettech.togethouse.Callback.ITerrainCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.AppartementModel;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.Model.MaisonModel;
import cm.togettech.togethouse.Model.StudioModel;
import cm.togettech.togethouse.Model.TerrainModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements
        IAppartementCallbackListener, IStudioCallbackListener, IChambreCallbackListener, IMaisonCallbackListener, ITerrainCallbackListener {

    private MutableLiveData<List<AppartementModel>> appartementList;
    private MutableLiveData<List<StudioModel>> studioList;
    private MutableLiveData<List<ChambreModel>> chambreList;
    private MutableLiveData<List<MaisonModel>> maisonList;
    private MutableLiveData<List<TerrainModel>> terrainList;
    private MutableLiveData<String> messageError;

    private IAppartementCallbackListener appartementCallbackListener;
    private IStudioCallbackListener studioCallbackListener;
    private IChambreCallbackListener chambreCallbackListener;
    private IMaisonCallbackListener maisonCallbackListener;
    private ITerrainCallbackListener terrainCallbackListener;

    public HomeViewModel() {
        appartementCallbackListener = this;
        studioCallbackListener = this;
        chambreCallbackListener = this;
        maisonCallbackListener = this;
        terrainCallbackListener = this;
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


    //Maison
    public MutableLiveData<List<MaisonModel>> getMaisonList() {
        if (maisonList == null){
            maisonList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadMaisonList();
        }
        return  maisonList;
    }

    //Maison
    public MutableLiveData<List<TerrainModel>> getTerrainList() {
        if (terrainList == null){
            terrainList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadTerrainList();
        }
        return  terrainList;
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

    // Chargement des maisons
    private void loadMaisonList() {
        List<MaisonModel>  tempList = new ArrayList<>();
        DatabaseReference maisonRef = FirebaseDatabase.getInstance().getReference(Common.MAISON_REF);
        maisonRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    MaisonModel model = itemSnapshot.getValue(MaisonModel.class);
                    tempList.add(model);
                }
                maisonCallbackListener.onMaisonLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                maisonCallbackListener.onMaisonLoadFailed(databaseError.getMessage());
            }
        });
    }

    // Chargement des terrains
    private void loadTerrainList() {
        List<TerrainModel>  tempList = new ArrayList<>();
        DatabaseReference terrainRef = FirebaseDatabase.getInstance().getReference(Common.TERRAIN_REF);
        terrainRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    TerrainModel terrainModel = itemSnapshot.getValue(TerrainModel.class);
                    tempList.add(terrainModel);
                }
                terrainCallbackListener.onTerrainLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                terrainCallbackListener.onTerrainLoadFailed(databaseError.getMessage());
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

    //Exceptions maison
    @Override
    public void onMaisonLoadSuccess(List<MaisonModel> maisonModels) {
        maisonList.setValue(maisonModels);
    }
    @Override
    public void onMaisonLoadFailed(String message) {
        messageError.setValue(message);
    }

    //Exceptions terrain
    @Override
    public void onTerrainLoadSuccess(List<TerrainModel> terrainModels) {
        terrainList.setValue(terrainModels);
    }
    @Override
    public void onTerrainLoadFailed(String message) {
        messageError.setValue(message);
    }
}