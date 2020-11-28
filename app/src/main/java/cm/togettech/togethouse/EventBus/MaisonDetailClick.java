package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.MaisonModel;

public class MaisonDetailClick {

    private boolean success;
    private MaisonModel maisonModel;

    public MaisonDetailClick(boolean success, MaisonModel maisonModel) {
        this.success = success;
        this.maisonModel = maisonModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MaisonModel getMaisonModel() {
        return maisonModel;
    }

    public void setMaisonModel(MaisonModel maisonModel) {
        this.maisonModel = maisonModel;
    }
}
