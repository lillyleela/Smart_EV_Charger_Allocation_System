package charger;
public abstract class Charger {

    private String chargerId;
    private String status;

    public Charger(String chargerId, String status) {
        this.chargerId = chargerId;
        this.status = status;
    }

    public String getChargerId() {
        return chargerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getChargerType();
}
