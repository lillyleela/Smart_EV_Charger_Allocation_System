package payment;
import java.util.*;
public abstract class Payment {

    private String paymentMethod;
    private double amount;
    private String transactionId;

    private static int transactionCounter = 1000;

    public Payment(String paymentMethod, double amount) {

        this.paymentMethod = paymentMethod;
        this.amount = amount;

        transactionId = "TXN" + (++transactionCounter);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public static double calculateCost(double energy, double ratePerUnit) {
        return energy * ratePerUnit;
    }
    public static String selectPaymentMethod(Scanner sc) {

        int choice;

        while (true) {

            System.out.println("\n========== PAYMENT METHOD ==========");
            System.out.println("1. UPI");
            System.out.println("2. Card");
            System.out.println("3. Cash");
            System.out.println("====================================");

            System.out.print("Select Payment Method: ");
            choice = sc.nextInt();

            if (choice == 1) {
                return "UPI";
            }
            else if (choice == 2) {
                return "Card";
            }
            else if (choice == 3) {
                return "Cash";
            }
            else {
                System.out.println("Invalid choice. Please select 1, 2 or 3.");
            }
        }
    }
    public abstract void makePayment();
}
