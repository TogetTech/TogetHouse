package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.AppartementModel;

public class AppartementItemClick {
    private boolean success;
    private AppartementModel appartementModel;

    public AppartementItemClick(boolean success, AppartementModel appartementModel) {
        this.success = success;
        this.appartementModel = appartementModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AppartementModel getAppartementModel() {
        return appartementModel;
    }

    public void setAppartementModel(AppartementModel appartementModel) {
        this.appartementModel = appartementModel;
    }
}
