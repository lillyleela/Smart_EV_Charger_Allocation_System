package charger;
import ui.*;
import java.util.*;
public class ChargingStation {

    private ArrayList<Charger> chargers;

    // Constructor
    public ChargingStation() {
        chargers = new ArrayList<Charger>();
        chargers.add(new NormalCharger("C001", "AVAILABLE"));
        chargers.add(new FastCharger("C002", "AVAILABLE"));
        chargers.add(new NormalCharger("C003", "AVAILABLE"));
        chargers.add(new FastCharger("C004", "AVAILABLE"));
        chargers.add(new NormalCharger("C005", "AVAILABLE"));
        chargers.add(new FastCharger("C006", "AVAILABLE"));
        chargers.add(new NormalCharger("C007", "AVAILABLE"));
        chargers.add(new FastCharger("C008", "AVAILABLE"));
        chargers.add(new NormalCharger("C009", "AVAILABLE"));
        chargers.add(new FastCharger("C010", "AVAILABLE"));
    }

    // Add charger
    public boolean addCharger(Charger charger) {

        // Check duplicate charger ID
        for (Charger c : chargers) {

            if (c.getChargerId().equalsIgnoreCase(charger.getChargerId())) {
                return false;
            }
        }

        chargers.add(charger);
        return true;
    }

    // Display all chargers
    public void viewChargers() {

        if (chargers.isEmpty()) {
            System.out.println("\nNo chargers available.");
            return;
        }
        int availableCount=0;
        System.out.println("\n========== CHARGER STATUS ==========");
        System.out.printf("%-12s %-15s %-15s%n",
                "CHARGER ID", "TYPE", "STATUS");

        System.out.println("------------------------------------------");

        for (Charger charger : chargers) {
                        String status = charger.getStatus();

            if (status.equalsIgnoreCase("AVAILABLE")) {

                status = ConsoleUI.GREEN + status + ConsoleUI.RESET;

            }
            else if (status.equalsIgnoreCase("OCCUPIED")) {

                status = ConsoleUI.RED + status + ConsoleUI.RESET;
            }
            System.out.printf("%-12s %-15s %-15s%n",
                    charger.getChargerId(),
                    charger.getChargerType(),
                    status);
            if (charger.getStatus().equalsIgnoreCase("AVAILABLE"))
            {
                availableCount++;
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Total Chargers     : " + chargers.size());
        System.out.println("Available Chargers : " + availableCount);
        
    }

    // Allocate charger according to required type
    public Charger allocateCharger(String requiredChargerType) {

        for (Charger charger : chargers) {

            if (charger.getStatus().equalsIgnoreCase("AVAILABLE")
                    && charger.getChargerType()
                            .equalsIgnoreCase(requiredChargerType)) {

                charger.setStatus("OCCUPIED");

                return charger;
            }
        }

        return null;
    }

    // Release charger after charging completion
    public void releaseCharger(Charger charger) {

        if (charger != null) {

            charger.setStatus("AVAILABLE");

            System.out.println("Charger " +
                    charger.getChargerId() +
                    " is now AVAILABLE.");
        }
    }
}
