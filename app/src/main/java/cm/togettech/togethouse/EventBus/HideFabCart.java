package cm.togettech.togethouse.EventBus;

public class HideFabCart {
    private boolean hidden;

    public HideFabCart(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
