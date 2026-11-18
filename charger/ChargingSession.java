package charger;
import ui.*;
public class ChargingSession {

    private static int sessionCounter = 500;

    private int sessionId;
    private ChargingRequest request;
    private Charger charger;
    private double energyConsumed;
    private double chargingCost;
    private double chargingRate;
    private String startDateTime;
    private String completionDateTime;
    private int endBatteryPercentage;
    private String status;

    public ChargingSession(ChargingRequest request, Charger charger) {

        sessionId = ++sessionCounter;
        this.request = request;
        this.charger = charger;

        energyConsumed = 0;
        chargingCost = 0;
        chargingRate=0;
        startDateTime = null;
        completionDateTime = null;

        status = "CHARGING";
    }

    public int getSessionId() {
        return sessionId;
    }

    public ChargingRequest getRequest() {
        return request;
    }

    public Charger getCharger() {
        return charger;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }
    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
    public double getChargingRate() {
        return chargingRate;
    }

    public double getChargingCost() {
        return chargingCost;
    }
    public String getStartDateTime() {
        return startDateTime;
    }

    public String getCompletionDateTime() {
        return completionDateTime;
    }
    public int getEndBatteryPercentage() {
        return endBatteryPercentage;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void startCharging() {

        status = "CHARGING";
        startDateTime =java.time.LocalDateTime.now().toString();
        request.setStatus("CHARGING");

        System.out.println("\n========== CHARGING SESSION STARTED ==========");
        System.out.println("Session ID   : " + sessionId);
        System.out.println("Vehicle      : "
                + request.getVehicle().getVehicleNumber());
        System.out.println("Charger ID   : " + charger.getChargerId());
        System.out.println("Charger Type : " + charger.getChargerType());
        System.out.println("Status       : " + status);
    }

    public void completeCharging() {

        if (status.equals("COMPLETED")) {
            System.out.println("Charging is already completed.");
            return;
        }

        // Battery percentage when charging is completed
        endBatteryPercentage = 80;

        // Calculate energy consumed
        energyConsumed =
                ((endBatteryPercentage -
                request.getVehicle().getBatteryPercentage()) / 100.0)
                * request.getVehicle().getBatteryCapacity();

        // Charging rate
        chargingRate = 12;

        // Calculate charging cost
        chargingCost = energyConsumed * chargingRate;

        // Completion time
        completionDateTime =
                java.time.LocalDateTime.now().toString();

        // Update status
        status = "COMPLETED";
        request.setStatus("COMPLETED");

        // Make charger available
        charger.setStatus("AVAILABLE");

        System.out.println("\n========== CHARGING COMPLETED ==========");
        System.out.println("Starting Battery : "
                + request.getVehicle().getBatteryPercentage() + "%");

        System.out.println("Ending Battery   : "
                + endBatteryPercentage + "%");

        System.out.println("Battery Capacity : "+ request.getVehicle().getBatteryCapacity() + " kWh");

        System.out.println("Energy Consumed  : "+ energyConsumed + " kWh");

        System.out.println("Charging Rate    : "+ chargingRate + "/kWh");

        System.out.println("Charging Cost    : "+ chargingCost);

        System.out.println("Status           : "+ status);
    }
}

