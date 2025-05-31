package itu.eval_2.newapp.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.action.ChildModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentEntryReference extends ChildModel {

    public PaymentEntryReference() {
        super("Payment Entry Reference");
        setParentfield("references");
        setParenttype("Parent Entry");
    }
    
    @JsonProperty("reference_doctype")
    private String referenceDoctype;

    @JsonProperty("reference_name")
    private String referenceName;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("payment_term_outstanding")
    private double paymentTermOutstanding;

    @JsonProperty("total_amount")
    private double totalAmount;

    @JsonProperty("outstanding_amount")
    private double outstandingAmount;

    @JsonProperty("allocated_amount")
    private double allocatedAmount;

    @JsonProperty("exchange_rate")
    private double exchangeRate;

    @JsonProperty("exchange_gain_loss")
    private double exchangeGainLoss;

    private String account;

    @JsonProperty("payment_request_outstanding")
    private double paymentRequestOutstanding;

    @Override
    public void update_cotnrole() {
        // Implementation for update control logic
    }

    @Override
    public void save_controle() {
    }
}
