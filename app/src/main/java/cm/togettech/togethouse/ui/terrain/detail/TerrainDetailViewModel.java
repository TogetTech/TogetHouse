package cm.togettech.togethouse.ui.terrain.detail;

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


import cm.togettech.togethouse.Callback.ITerrainCallbackListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.TerrainModel;

public class TerrainDetailViewModel extends ViewModel implements ITerrainCallbackListener {

    private MutableLiveData<TerrainModel> mutableLiveDataTerrainList;

    public MutableLiveData<TerrainModel> getMutableLiveDataTerrain() {
        if (mutableLiveDataTerrainList == null)
            mutableLiveDataTerrainList = new MutableLiveData<>();
        mutableLiveDataTerrainList.setValue(Common.selectedTerrain);
        return mutableLiveDataTerrainList;
    }

    public void setTerrainModel(TerrainModel terrainModel) {
        if (mutableLiveDataTerrainList !=null)
            mutableLiveDataTerrainList.setValue(terrainModel);
    }

    private MutableLiveData<List<TerrainModel>> terrainList;
    private MutableLiveData<String> messageError;
    private ITerrainCallbackListener terrainCallbackListener;

    public TerrainDetailViewModel() {
        terrainCallbackListener = this;
    }

    public MutableLiveData<List<TerrainModel>> getTerrainList() {
        if (terrainList == null){
            terrainList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadTerrainList();
        }
        return terrainList;
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

    @Override
    public void onTerrainLoadSuccess(List<TerrainModel> terrainModels) {
        terrainList.setValue(terrainModels);
    }
    @Override
    public void onTerrainLoadFailed(String message) {
        messageError.setValue(message);
    }
}
