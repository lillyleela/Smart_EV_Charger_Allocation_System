package payment;
public class UPIPayment extends Payment {

    public UPIPayment(double amount) {
        super("UPI",amount);
    }

    public void makePayment() {
        System.out.println("Payment through UPI");
        System.out.println("Amount: " + getAmount());
    }
}
