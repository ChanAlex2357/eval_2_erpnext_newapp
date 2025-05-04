package itu.eval_2.newapp.enums;

public enum PaymentType {
    PAY("Pay"),
    RECEIVE("Receive");

    private final String type;

    private PaymentType(String t){
        this.type = t;
    }

    public String getType() {
        return type;
    }
}
