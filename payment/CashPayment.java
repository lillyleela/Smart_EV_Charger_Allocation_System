package payment;
public class CashPayment extends Payment {

    public CashPayment(double amount) {
        super("Cash",amount);
    }

    public void makePayment() {
        System.out.println("Payment through Cash");
        System.out.println("Amount: " + getAmount());
    }
}
