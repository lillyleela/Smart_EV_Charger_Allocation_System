package payment;
import Vehicle.*;
import charger.*;
import ui.*;
import charger.ChargingSession;
public class Receipt {

    private static int receiptCounter = 1000;

    private int receiptId;
    private ChargingSession session;
    private Payment payment;

    public Receipt(ChargingSession session, Payment payment) {

        receiptId = ++receiptCounter;

        this.session = session;
        this.payment = payment;
    }
    public ChargingSession getSession() {
        return session;
        }

    public void displayReceipt() {

        Vehicle vehicle = session.getRequest().getVehicle();
        System.out.println("\n==============================================");
        System.out.println("          SMART EV CHARGING RECEIPT");
        System.out.println("==============================================");

        System.out.println("\n----- RECEIPT DETAILS -----");
        System.out.println("Receipt ID        : " + receiptId);

        System.out.println("\n----- VEHICLE DETAILS -----");
        System.out.println("Vehicle Number    : "
                + vehicle.getVehicleNumber());

        System.out.println("Vehicle Type      : "
                + vehicle.getVehicleType());

        System.out.println("Battery Arrived   : "
                + vehicle.getBatteryPercentage() + "%");

        System.out.println("Battery Completed : "
                + session.getEndBatteryPercentage() + "%");

        System.out.println("Arrival Time      : "
                + vehicle.getEnteredDateTime());

        System.out.println("\n----- CHARGING DETAILS -----");
        System.out.println("Request ID        : "
                + session.getRequest().getRequestId());

        System.out.println("Session ID        : "
                + session.getSessionId());

        System.out.println("Charger ID        : "
                + session.getCharger().getChargerId());

        System.out.println("Charger Type      : "
                + session.getCharger().getChargerType());

        System.out.println("Charging Start    : "
                + session.getStartDateTime());

        System.out.println("Charging Completed: "
                + session.getCompletionDateTime());

        System.out.println("Energy Consumed   : "
                + session.getEnergyConsumed() + " kWh");

        System.out.println("Charging Rate     : "
                + session.getChargingRate() + "/kWh");

        System.out.printf("Total Amount      : %.2f%n",
                payment.getAmount());

        System.out.println("\n----- PAYMENT DETAILS -----");
        System.out.println("Payment Method    : "
                + payment.getPaymentMethod());

        System.out.println("Transaction ID    : "
                + payment.getTransactionId());

        System.out.println("\n----- STATUS -----");
        System.out.println("Charging Status   : "
                + session.getStatus());

        System.out.println("\n==============================================");
        System.out.println("              THANK YOU");
        System.out.println("==============================================");
    }
}


