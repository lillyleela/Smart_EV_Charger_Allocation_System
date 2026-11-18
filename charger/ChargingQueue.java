package charger;
import ui.*;
import java.util.*;
public class ChargingQueue {

    private Queue<ChargingRequest> requests;

    public ChargingQueue() {
        requests = new LinkedList<ChargingRequest>();
    }

    public void addRequest(ChargingRequest request) {
        requests.add(request);
        ConsoleUI.info("Request added to waiting queue.");
    }

    public ChargingRequest getNextRequest() {
        return requests.poll();
    }

    public ChargingRequest viewNextRequest() {
        return requests.peek();
    }

    public boolean isEmpty() {
        return requests.isEmpty();
    }

    public void viewQueue() {

        if (requests.isEmpty()) {
            ConsoleUI.info("\nWaiting queue is empty.");
            return;
        }

        System.out.println("\n========== WAITING QUEUE ==========");
        System.out.printf("%-10s %-15s %-15s %-10s%n",
                "POSITION", "REQUEST ID", "VEHICLE", "TYPE");

        System.out.println("----------------------------------------------");

        int position = 1;

        for (ChargingRequest request : requests) {

            System.out.printf("%-10d %-15d %-15s %-10s%n",
                    position,
                    request.getRequestId(),
                    request.getVehicle().getVehicleNumber(),
                    request.getRequiredChargingType());

            position++;
        }

        System.out.println("----------------------------------------------");
    }
}
