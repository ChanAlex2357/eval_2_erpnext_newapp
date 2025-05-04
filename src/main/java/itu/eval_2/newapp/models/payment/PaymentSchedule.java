package itu.eval_2.newapp.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.action.ChildModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentSchedule extends ChildModel {
    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("invoice_portion")
    private double invoicePortion;

    @JsonProperty("discount_type")
    private String discountType;

    private double discount;

    @JsonProperty("payment_amount")
    private double paymentAmount;

    private double outstanding;

    @JsonProperty("paid_amount")
    private double paidAmount;

    @JsonProperty("discounted_amount")
    private double discountedAmount;

    @JsonProperty("base_payment_amount")
    private double basePaymentAmount;

    @JsonProperty("base_outstanding")
    private double baseOutstanding;

    @JsonProperty("base_paid_amount")
    private double basePaidAmount;

    @Override
    public void cotnrole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cotnrole'");
    }
}
