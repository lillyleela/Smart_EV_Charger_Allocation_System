package charger;
public class FastCharger extends Charger {

    public FastCharger(String chargerId, String status) {
        super(chargerId, status);
    }

    @Override
    public String getChargerType() {
        return "FAST";
    }
}
