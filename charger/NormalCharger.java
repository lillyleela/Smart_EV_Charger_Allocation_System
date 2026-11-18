package charger;
public class NormalCharger extends Charger {

    public NormalCharger(String chargerId, String status) {
        super(chargerId, status);
    }

    @Override
    public String getChargerType() {
        return "NORMAL";
    }
}
