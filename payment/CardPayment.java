package payment;
public class CardPayment extends Payment {

    public CardPayment(double amount) {
        super("Card",amount);
    }

    public void makePayment() {
        System.out.println("Payment through Card");
        System.out.println("Amount: " + getAmount());
    }
}
