package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.TerrainModel;

public class TerrainItemClick {

    private boolean success;
    private TerrainModel terrainModel;

    public TerrainItemClick(boolean success, TerrainModel terrainModel) {
        this.success = success;
        this.terrainModel = terrainModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TerrainModel getTerrainModel() {
        return terrainModel;
    }

    public void setTerrainModel(TerrainModel terrainModel) {
        this.terrainModel = terrainModel;
    }
}
