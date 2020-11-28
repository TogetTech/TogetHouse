package cm.togettech.togethouse.Callback;

import java.util.List;

import cm.togettech.togethouse.Model.TerrainModel;

public interface ITerrainCallbackListener {
    void onTerrainLoadSuccess(List<TerrainModel> terrainModels);
    void onTerrainLoadFailed(String message);
}
