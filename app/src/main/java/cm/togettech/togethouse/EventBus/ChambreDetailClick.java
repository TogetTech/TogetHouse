package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.ChambreModel;

public class ChambreDetailClick {
    private boolean success;
    private ChambreModel chambreModel;

    public ChambreDetailClick(boolean success, ChambreModel chambreModel) {
        this.success = success;
        this.chambreModel = chambreModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ChambreModel getChambreModel() {
        return chambreModel;
    }

    public void setChambreModel(ChambreModel chambreModel) {
        this.chambreModel = chambreModel;
    }
}
