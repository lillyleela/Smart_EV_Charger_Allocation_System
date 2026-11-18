package charger;
import Vehicle.*;
public class ChargingRequest {

    private static int requestCounter = 100;

    private int requestId;
    private Vehicle vehicle;
    private String requiredChargingType;
    private String priority;
    private String status;

    public ChargingRequest(Vehicle vehicle, String requiredChargingType,
                           String priority) {

        requestId = ++requestCounter;
        this.vehicle = vehicle;
        this.requiredChargingType = requiredChargingType;
        this.priority = priority;

        status = "WAITING";
    }

    public int getRequestId() {
        return requestId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getRequiredChargingType() {
        return requiredChargingType;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
