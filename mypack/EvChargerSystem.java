package mypack;
import java.util.*;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Vehicle.*;
import register.*;
import ui.ConsoleUI;
import allocation.*;
import charger.*;
import payment.*;
//import payment.Receipt;
public class EvChargerSystem {
    static Scanner sc=new Scanner(System.in);
    static void display(Vehicle v)
    {
        System.out.println("Vehicle Number is: "+v.getVehicleNumber().toUpperCase());
        System.out.println("Vehicle Type: "+v.getVehicleType());
        System.out.println("Vehicle Battery Percentage "+v.getBatteryPercentage());
        System.out.println("Vehicle Charger Type: " +v.getRequiredChargerType());
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Entered Date & Time: "
            + v.getEnteredDateTime().format(formatter));
    }
     static void allocateNextWaitingVehicle(
                ChargingQueue queue,
                ChargingStation station,
                ArrayList<ChargingSession> sessionList) {

            while (!queue.isEmpty()) {

                // Get the first waiting request without removing it
                ChargingRequest request = queue.viewNextRequest();

                // Try to allocate an available compatible charger
                Charger charger = station.allocateCharger(
                        request.getRequiredChargingType());

                // If no compatible charger is available, stop
                if (charger == null) {
                    System.out.println(
                            "No compatible charger available for waiting vehicles.");
                    break;
                }

                // Remove request only after successful allocation
                queue.getNextRequest();

                request.setStatus("ALLOCATED");

                // Create a new charging session
                ChargingSession newSession =
                        new ChargingSession(request, charger);

                newSession.startCharging();

                // Store the new session
                sessionList.add(newSession);

                System.out.println(
                        "Waiting vehicle allocated successfully!");
            }
        }
   static void startChargerSystem()
   {
        ConsoleUI.startupAnimation();
        Random random = new Random();

        ArrayList<Login> user=new ArrayList<>();
        boolean found=true;
        
        while(found)
        {
            ConsoleUI.title("SMART EV CHARGING STATION");
            System.out.println(ConsoleUI.CYAN+"1."+ConsoleUI.RESET+"Register");
            System.out.println(ConsoleUI.CYAN+"2."+ConsoleUI.RESET+"Login");
            System.out.println(ConsoleUI.CYAN+"3."+ConsoleUI.RESET+"Exit");
            ConsoleUI.line();
            System.out.print(ConsoleUI.YELLOW + "Enter your choice: " + ConsoleUI.RESET);
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                    ConsoleUI.title("REGISTER");
                    sc.nextLine();
                    ConsoleUI.input("Enter Username: ");
                    String username = sc.nextLine();
                    ConsoleUI.input("Enter Phone Number: ");
                    long phoneNumber=sc.nextLong();
                    int attempts = 0;

                    while (!Login.validatePhoneNumber(phoneNumber)) {

                        attempts++;

                        ConsoleUI.error("Invalid Phone Number!");

                        ConsoleUI.input("Enter Phone Number: ");
                        phoneNumber = sc.nextLong();

                        Login.validatePhoneNumber(phoneNumber);

                        if (attempts == 3) {

                            ConsoleUI.error("Maximum attempts reached.");

                            main(null);
                        }
                    }

                    ConsoleUI.input("Enter your Email Id: ");
                    String emailId = sc.next();

                    attempts = 0;

                    while (!Login.validateEmail(emailId)) {

                        ConsoleUI.error("Invalid Email Id!");

                        ConsoleUI.input("Enter your Email Id: ");
                        emailId = sc.next();

                        attempts++;

                        if (attempts == 3) {

                            ConsoleUI.error("Maximum attempts reached.");

                            main(null);
                        }
                    }

                    boolean alreadyRegistered = false;

                    for (int i = 0; i < user.size(); i++) {

                        if (user.get(i).getPhoneNumber() == phoneNumber
                                || user.get(i).getEmailId().equals(emailId)) {

                            alreadyRegistered = true;
                            break;
                        }
                    }

                    if (alreadyRegistered) {

                        ConsoleUI.error(
                                "User is already registered! Please Login."
                        );

                    } else {

                        Login login =
                                new Login(username, phoneNumber, emailId);

                        user.add(login);

                        ConsoleUI.success("Registration Successful!");
                    }
                    break;
                case 2:
                    ConsoleUI.title("LOGIN");
                    ConsoleUI.input("Enter Phone Number:");
                    long enteredPhone = sc.nextLong();
                    Login loginUser = null;
                    for (int i = 0; i < user.size(); i++) {
                        if (user.get(i).validatePhoneNumber(enteredPhone)) {
                            loginUser = user.get(i);
                            break;
                        }
                    }
                    if (loginUser == null || loginUser.getPhoneNumber()!=enteredPhone) {
                        ConsoleUI.error("User is not registered.");
                        ConsoleUI.warning("Please register first.");;
                        main(null);
                    }
                    else
                    {
                        if (loginUser != null) {
                        // alreadyRegistered=false;
                        // for (int i = 0; i < user.size(); i++) {

                        //     if (user.get(i).getPhoneNumber() == phoneNumber
                        //             || user.get(i).getEmailId().equals(emailId)) {

                        //         alreadyRegistered = true;
                        //         break;
                        //     }
                        // }
                        int generatedOTP = 1000 + random.nextInt(9000);

                        ConsoleUI.info("\nOTP Generated: " + generatedOTP);

                        ConsoleUI.input("Enter OTP: ");
                        int enteredOTP = sc.nextInt();

                        if (loginUser.validateOTP(enteredOTP, generatedOTP)) {

                            ConsoleUI.success("\nLogin Successful!");
                            ConsoleUI.success("Welcome " + loginUser.getUserName() + "!");
                            // ChargingQueue queue = new ChargingQueue();
                            // ChargingStation station = new ChargingStation();
                            // Vehicle v=null;
                            // ChargingSession session=null;
                            // Receipt receipt=null;
                            ChargingQueue queue = new ChargingQueue();
                            ChargingStation station = new ChargingStation();

                            Vehicle v = null;


                            ArrayList<ChargingSession> sessionList = new ArrayList<>();
                            ArrayList<Receipt> receiptList = new ArrayList<>();
                            boolean found1=true;
                            EVAllocationSystem system = new EVAllocationSystem();
                            // remaining menu
                            while (found1) {

                                ConsoleUI.title("SMART EV CHARGER ");

                                System.out.println(ConsoleUI.CYAN + "1. " + ConsoleUI.RESET + "User Profile");
                                System.out.println(ConsoleUI.CYAN + "2. " + ConsoleUI.RESET + "View Chargers");
                                System.out.println(ConsoleUI.CYAN + "3. " + ConsoleUI.RESET + "Vehicle Management");
                                System.out.println(ConsoleUI.CYAN + "4. " + ConsoleUI.RESET + "View Charging Queue");
                                System.out.println(ConsoleUI.CYAN + "5. " + ConsoleUI.RESET + "View Current Charging Session");
                                System.out.println(ConsoleUI.CYAN + "6. " + ConsoleUI.RESET + "Complete Charging");
                                System.out.println(ConsoleUI.CYAN + "7. " + ConsoleUI.RESET + "View Receipt");
                                System.out.println(ConsoleUI.CYAN + "8. " + ConsoleUI.RESET + "Exit");
                                ConsoleUI.line();
                                System.out.print(ConsoleUI.YELLOW + "Enter your choice: " + ConsoleUI.RESET);
                                int selectChoice = sc.nextInt();
                                switch (selectChoice) {
                                    case 1:
                                        ConsoleUI.title(loginUser.getUserName() + " PROFILE");
                                        System.out.println(
                                                ConsoleUI.CYAN + "| Name        : "
                                                + ConsoleUI.RESET + loginUser.getUserName());

                                        System.out.println(
                                                ConsoleUI.CYAN + "| Phone Number: "
                                                + ConsoleUI.RESET + loginUser.getPhoneNumber());

                                        System.out.println(
                                                ConsoleUI.CYAN + "| Email ID    : "
                                                + ConsoleUI.RESET + loginUser.getEmailId());

                                        ConsoleUI.line();
                                    break;
                                    case 2:
                                        ConsoleUI.title("CHARGER AVAILABILITY");
                                        station.viewChargers();
                                        ConsoleUI.line();
                                    break;

                                    case 3:
                                        ConsoleUI.title("VEHICLE MANAGEMENT");

                                        ConsoleUI.input("Enter your Vehicle Number: ");
                                        String vehicleNumber = sc.next();

                                        attempts = 0;

                                        while (!Vehicle.validateVehicleNumber(vehicleNumber)) {

                                            ConsoleUI.error("Invalid Vehicle Number!");
                                            System.out.println("Completed attempts "+attempts++);
                                            if(attempts==3)
                                            {
                                                break;
                                            }
                                            ConsoleUI.input("Enter your Vehicle Number: ");
                                            vehicleNumber = sc.next();
                                            sc.nextLine();

                                            // attempts++;
                                            
                                            

                                            
                                        }
                                        if (attempts==3) {

                                            ConsoleUI.error("Maximum attempts reached.");
                                            //System.out.println("break");
                                            break;
                                        }

                                        String vehicleType = Vehicle.getVehicleType(sc);

                                        ConsoleUI.input("Enter your Battery Percentage: ");
                                        int batteryPercentage = sc.nextInt();

                                        attempts = 0;

                                        while (!Vehicle.validateBattery(batteryPercentage)) {

                                            ConsoleUI.error(
                                                    "Battery percentage must be between 0 and 100."
                                            );

                                            ConsoleUI.input("Enter your Battery Percentage: ");
                                            batteryPercentage = sc.nextInt();

                                            attempts++;

                                            if (attempts > 3) {

                                                ConsoleUI.error("Maximum attempts reached.");
                                                break;
                                            }
                                        }


                                        String requiredChargerType =
                                                Vehicle.getChargerType(sc);
                                        if(requiredChargerType.equalsIgnoreCase("NORMAL"))
                                        {

                                        }
                                        double batteryCapacity = 0;
                                        try {

                                            ConsoleUI.input("Enter your Battery Capacity (kWh): ");
                                            batteryCapacity = sc.nextDouble();

                                        }
                                        catch (Exception e) {

                                            ConsoleUI.error(e.getMessage());
                                        }
                                        boolean vehicleExists = false;

                                        for (int i = 0;
                                                i < system.getVehicleList().size();
                                                i++) {

                                            if (system.getVehicleList().get(i)
                                                    .getVehicleNumber()
                                                    .equalsIgnoreCase(vehicleNumber)) {

                                                vehicleExists = true;
                                                break;
                                            }
                                        }
                                        if (vehicleExists) {

                                            ConsoleUI.error(
                                                    "Vehicle is already registered. "
                                                    + "Please use another vehicle."
                                            );

                                        }
                                        else {
                                            if(vehicleType.equalsIgnoreCase("CAR"))
                                            {
                                                v = new Car(
                                                        vehicleNumber,
                                                        vehicleType,
                                                        batteryPercentage,
                                                        requiredChargerType,
                                                        batteryCapacity
                                                );
                                            }
                                            else if(vehicleType.equalsIgnoreCase("BIKE"))
                                            {
                                                v = new Vehicle(
                                                        vehicleNumber,
                                                        vehicleType,
                                                        batteryPercentage,
                                                        requiredChargerType,
                                                        batteryCapacity
                                                );
                                            }
                                               // Add vehicle to the list
                                            system.getVehicleList().add(v);
                                            ConsoleUI.success(
                                                    "Vehicle details registered successfully."
                                            );


                                            ConsoleUI.title("CHARGING REQUEST");


                                            ChargingRequest request =
                                                    new ChargingRequest(
                                                            v,
                                                            v.getRequiredChargerType(),
                                                            "NORMAL"
                                                    );


                                            Charger allocatedCharger =
                                                    station.allocateCharger(
                                                            request.getRequiredChargingType()
                                                    );


                                            if (allocatedCharger != null) {

                                                request.setStatus("ALLOCATED");

                                                ConsoleUI.success(
                                                        "Charging Request Accepted!"
                                                );


                                                display(v);

                                                ConsoleUI.line();


                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "Request ID   : "
                                                        + ConsoleUI.RESET
                                                        + request.getRequestId()
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "Vehicle      : "
                                                        + ConsoleUI.RESET
                                                        + request.getVehicle()
                                                                .getVehicleNumber()
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "Charger ID   : "
                                                        + ConsoleUI.RESET
                                                        + allocatedCharger.getChargerId()
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "Charger Type : "
                                                        + ConsoleUI.RESET
                                                        + allocatedCharger.getChargerType()
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "Status       : "
                                                        + ConsoleUI.GREEN
                                                        + request.getStatus()
                                                        + ConsoleUI.RESET
                                                );


                                                // session =
                                                //         new ChargingSession(
                                                //                 request,
                                                //                 allocatedCharger
                                                //         );

                                                //session.startCharging();
                                                ChargingSession session = new ChargingSession(request, allocatedCharger);

                                                session.startCharging();

                                                sessionList.add(session);


                                                ConsoleUI.line();

                                            }
                                            else {

                                                ConsoleUI.warning(
                                                        "No charger available."
                                                );

                                                ConsoleUI.section(
                                                        "WAITING QUEUE OPTIONS"
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "1. "
                                                        + ConsoleUI.RESET
                                                        + "Join Waiting Queue"
                                                );

                                                System.out.println(
                                                        ConsoleUI.CYAN
                                                        + "2. "
                                                        + ConsoleUI.RESET
                                                        + "Exit"
                                                );

                                                ConsoleUI.input(
                                                        "Enter your choice: "
                                                );

                                                int choiceQueue = sc.nextInt();


                                                if (choiceQueue == 1) {

                                                    queue.addRequest(request);

                                                    display(v);

                                                    ConsoleUI.success(
                                                            "Request added to waiting queue."
                                                    );

                                                    System.out.println(
                                                            "Request ID : "
                                                            + request.getRequestId()
                                                    );

                                                    System.out.println(
                                                            "Status     : "
                                                            + ConsoleUI.YELLOW
                                                            + "IN QUEUE"
                                                            + ConsoleUI.RESET
                                                    );

                                                    ConsoleUI.warning(
                                                            "Vehicle is in queue."
                                                    );

                                                    queue.viewQueue();

                                                }
                                                else {

                                                    ConsoleUI.info(
                                                            "Vehicle has left the EV Charging Station."
                                                    );

                                                    request.setStatus("CANCELLED");
                                                }
                                            }
                                        }

                                    break;

                                    case 4://view the chargig queue
                                         ConsoleUI.title("WAITING QUEUE");

                                        queue.viewQueue();

                                        ConsoleUI.line();

                                        break;
                                    // case 5:
                                    //     ConsoleUI.title("CURRENT CHARGING SESSION");
                                    //     if (session != null) {

                                    //         System.out.println(
                                    //                 ConsoleUI.CYAN
                                    //                 + "Session ID   : "
                                    //                 + ConsoleUI.RESET
                                    //                 + session.getSessionId()
                                    //         );

                                    //         System.out.println(
                                    //                 ConsoleUI.CYAN
                                    //                 + "Vehicle      : "
                                    //                 + ConsoleUI.RESET
                                    //                 + session.getRequest()
                                    //                         .getVehicle()
                                    //                         .getVehicleNumber()
                                    //         );

                                    //         System.out.println(
                                    //                 ConsoleUI.CYAN
                                    //                 + "Charger ID   : "
                                    //                 + ConsoleUI.RESET
                                    //                 + session.getCharger()
                                    //                         .getChargerId()
                                    //         );

                                    //         System.out.println(
                                    //                 ConsoleUI.CYAN
                                    //                 + "Charger Type : "
                                    //                 + ConsoleUI.RESET
                                    //                 + session.getCharger()
                                    //                         .getChargerType()
                                    //         );

                                    //         System.out.println(
                                    //                 ConsoleUI.CYAN
                                    //                 + "Status       : "
                                    //                 + ConsoleUI.GREEN
                                    //                 + session.getStatus()
                                    //                 + ConsoleUI.RESET
                                    //         );

                                    //         ConsoleUI.line();

                                    //     }
                                    //     else {

                                    //         ConsoleUI.error(
                                    //                 "No active charging session found."
                                    //         );

                                    //         ConsoleUI.info(
                                    //                 "Charger has not been allocated to this vehicle."
                                    //         );
                                    //     }

                                    // break;

                                    case 5:

                                        ConsoleUI.title("CURRENT CHARGING SESSIONS");

                                        boolean activeSessionFound = false;

                                        System.out.printf("%-12s %-18s %-12s %-15s%n",
                                                "SESSION ID", "VEHICLE NUMBER",
                                                "CHARGER ID", "STATUS");

                                        System.out.println("----------------------------------------------------------");

                                        for (int i = 0; i < sessionList.size(); i++) {

                                            ChargingSession currentSession = sessionList.get(i);

                                            if (currentSession.getStatus().equalsIgnoreCase("CHARGING")) {

                                                activeSessionFound = true;

                                                System.out.printf("%-12d %-18s %-12s %-15s%n",
                                                        currentSession.getSessionId(),
                                                        currentSession.getRequest().getVehicle().getVehicleNumber(),
                                                        currentSession.getCharger().getChargerId(),
                                                        currentSession.getStatus());
                                            }
                                        }

                                        if (!activeSessionFound) {

                                            ConsoleUI.error("No active charging sessions found.");

                                        }

                                        ConsoleUI.line();

                                        break;

                                    // case 6:

                                    //     ConsoleUI.title("COMPLETE CHARGING");

                                    //     if (session != null) {

                                    //         if (session.getStatus().equals("CHARGING")) {

                                    //             session.completeCharging();
                                    //             //session.completeCharging();

                                    //             // Release the completed vehicle's charger
                                    //             station.releaseCharger(session.getCharger());

                                    //             // Process waiting queue
                                    //             allocateNextWaitingVehicle();

                                    //             double amount =
                                    //                     Payment.calculateCost(
                                    //                             session.getEnergyConsumed(),
                                    //                             session.getChargingRate()
                                    //                     );

                                    //             String paymentMethod =
                                    //                     Payment.selectPaymentMethod(sc);

                                    //             // Parent class reference
                                    //             Payment payment;

                                    //             if (paymentMethod.equalsIgnoreCase("UPI")) {

                                    //                 payment = new UPIPayment(amount);

                                    //             }
                                    //             else if (paymentMethod.equalsIgnoreCase("Card")) {

                                    //                 payment = new CardPayment(amount);

                                    //             }
                                    //             else {

                                    //                 payment = new CashPayment(amount);
                                    //             }

                                    //             // Runtime polymorphism
                                    //             payment.makePayment();

                                    //             receipt =
                                    //                     new Receipt(
                                    //                             session,
                                    //                             payment
                                    //                     );

                                    //             ConsoleUI.success(
                                    //                     "Charging completed successfully."
                                    //             );

                                    //             ConsoleUI.success(
                                    //                     "Payment processed successfully."
                                    //             );

                                    //             ConsoleUI.info(
                                    //                     "Transaction ID : "
                                    //                     + payment.getTransactionId()
                                    //             );

                                    //         }
                                    //         else {

                                    //             ConsoleUI.error(
                                    //                     "Charging session is not active."
                                    //             );
                                    //         }

                                    //     }
                                    //     else {

                                    //         ConsoleUI.error(
                                    //                 "No active charging session found."
                                    //         );
                                    //     }

                                    // break;

                                case 6:

                                    ConsoleUI.title("COMPLETE CHARGING");

                                    boolean activeSessionFound1= false;

                                    // Display active charging vehicles
                                    for (int i = 0; i < sessionList.size(); i++) {

                                        ChargingSession currentSession = sessionList.get(i);

                                        if (currentSession.getStatus().equalsIgnoreCase("CHARGING")) {

                                            activeSessionFound1 = true;

                                            System.out.println(
                                                    "Vehicle Number : " +
                                                    currentSession.getRequest().getVehicle().getVehicleNumber()
                                            );

                                            System.out.println(
                                                    "Session ID     : " + currentSession.getSessionId()
                                            );

                                            System.out.println(
                                                    "Charger ID     : " +
                                                    currentSession.getCharger().getChargerId()
                                            );

                                            System.out.println("--------------------------------");
                                        }
                                    }

                                    if (!activeSessionFound1) {

                                        ConsoleUI.error("No active charging sessions found.");
                                        break;
                                    }

                                    // Select vehicle
                                    ConsoleUI.input("Enter Vehicle Number to complete charging: ");
                                    String enteredVehicle = sc.next();

                                    ChargingSession selectedSession = null;

                                    // Find the selected vehicle's active session
                                    for (int i = 0; i < sessionList.size(); i++) {

                                        ChargingSession currentSession = sessionList.get(i);

                                        if (currentSession.getRequest().getVehicle()
                                                .getVehicleNumber().equalsIgnoreCase(enteredVehicle)
                                                && currentSession.getStatus().equalsIgnoreCase("CHARGING")) {

                                            selectedSession = currentSession;
                                            break;
                                        }
                                    }

                                    if (selectedSession == null) {

                                        ConsoleUI.error("No active charging session found for this vehicle.");
                                        break;
                                    }

                                    // Complete selected vehicle charging
                                    selectedSession.completeCharging();

                                    // Charger automatically becomes AVAILABLE inside completeCharging()

                                    double amount = Payment.calculateCost(
                                            selectedSession.getEnergyConsumed(),
                                            selectedSession.getChargingRate()
                                    );

                                    String paymentMethod = Payment.selectPaymentMethod(sc);

                                    Payment payment;

                                    if (paymentMethod.equalsIgnoreCase("UPI")) {

                                        payment = new UPIPayment(amount);

                                    }
                                    else if (paymentMethod.equalsIgnoreCase("Card")) {

                                        payment = new CardPayment(amount);

                                    }
                                    else {

                                        payment = new CashPayment(amount);
                                    }

                                    // Runtime polymorphism
                                    payment.makePayment();

                                    // Generate receipt
                                    Receipt newReceipt = new Receipt(selectedSession, payment);

                                    receiptList.add(newReceipt);

                                    ConsoleUI.success("Charging completed successfully.");

                                    ConsoleUI.success("Payment processed successfully.");

                                    ConsoleUI.info(
                                            "Transaction ID : " + payment.getTransactionId()
                                    );

                                    // Automatically allocate released charger to waiting vehicle
                                    allocateNextWaitingVehicle(queue, station, sessionList);

                                    break;

                                    // case 7:
                                    //     ConsoleUI.title("CHARGING RECEIPT");
                                    //     if (session != null
                                    //             && session.getStatus().equals("COMPLETED")
                                    //             && receipt != null) {

                                    //         receipt.displayReceipt();

                                    //     }
                                    //     else {

                                    //         ConsoleUI.error(
                                    //                 "Charging is not completed."
                                    //         );
                                    //     }

                                    //     break;

                                        case 7:

                                            ConsoleUI.title("CHARGING RECEIPT");

                                            boolean receiptFound = false;

                                            // Display completed vehicle receipts
                                            for (int i = 0; i < receiptList.size(); i++) {

                                                Receipt currentReceipt = receiptList.get(i);

                                                receiptFound = true;

                                                System.out.println(
                                                        (i + 1) + ". " +
                                                        currentReceipt.getSession().getRequest()
                                                                .getVehicle().getVehicleNumber()
                                                );
                                            }

                                            if (!receiptFound) {

                                                ConsoleUI.error("No charging receipts available.");
                                                break;
                                            }

                                            ConsoleUI.input("Enter Vehicle Number to view receipt: ");

                                            String receiptVehicle = sc.next();

                                            boolean foundReceipt = false;

                                            for (int i = 0; i < receiptList.size(); i++) {

                                                Receipt currentReceipt = receiptList.get(i);

                                                if (currentReceipt.getSession().getRequest()
                                                        .getVehicle().getVehicleNumber()
                                                        .equalsIgnoreCase(receiptVehicle)) {

                                                    currentReceipt.displayReceipt();

                                                    foundReceipt = true;
                                                    break;
                                                }
                                            }

                                            if (!foundReceipt) {

                                                ConsoleUI.error("Receipt not found for this vehicle.");
                                            }

                                            break;

                                     case 8:
                                        ConsoleUI.title("EXIT");
                                        ConsoleUI.success(
                                                "Thank you for using Smart EV Charger System!"
                                        );

                                        found1 = false;

                                        main(null);

                                    break;

                                    default:

                                        ConsoleUI.error(
                                                "Invalid Choice! Please select a valid option."
                                        );
                                }
                            }
                        }
                        else {
                            ConsoleUI.error("\nInvalid OTP!");
                            ConsoleUI.error("Login Failed!");
                            main(null);
                        }

                    }
                        else {
                            ConsoleUI.error("\nPhone Number does not match!");
                            ConsoleUI.error("Login Failed!");
                            main(null);
                        } 
                    }
                                               
                break;
                case 3:
                    ConsoleUI.info("Thankyou for choosing our EV Charger System");
                    ConsoleUI.success("Please Visit Again!");
                    System.exit(0);
                    found=false;
                    break;
                default:
                    ConsoleUI.info("Thankyou for choosing our EV Charger System");
                    found=false;

            }
        } 
   }
    public static void main(String[] args) {

       

        startChargerSystem();
        sc.close();
    }
}