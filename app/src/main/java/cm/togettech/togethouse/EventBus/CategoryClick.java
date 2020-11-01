package cm.togettech.togethouse.EventBus;

import cm.togettech.togethouse.Model.StudioModel;

public class CategoryClick {
    private boolean success;
    private StudioModel categoryModel;

    public CategoryClick(boolean success, StudioModel categoryModel) {
        this.success = success;
        this.categoryModel = categoryModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public StudioModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(StudioModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}
