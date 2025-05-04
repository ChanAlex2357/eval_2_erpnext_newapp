package itu.eval_2.newapp.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentEntry extends FrappeDocument {

    public PaymentEntry() {
        super("Payment Entry");
    }

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("payment_order_status")
    private String paymentOrderStatus;

    @JsonProperty("posting_date")
    private String postingDate;

    @JsonProperty("party_type")
    private String partyType;

    private String party;

    @JsonProperty("party_name")
    private String partyName;

    @JsonProperty("book_advance_payments_in_separate_party_account")
    private int bookAdvancePaymentsInSeparatePartyAccount;

    @JsonProperty("reconcile_on_advance_payment_date")
    private int reconcileOnAdvancePaymentDate;

    @JsonProperty("advance_reconciliation_takes_effect_on")
    private String advanceReconciliationTakesEffectOn;

    @JsonProperty("paid_from")
    private String paidFrom;

    @JsonProperty("paid_from_account_type")
    private String paidFromAccountType;

    @JsonProperty("paid_from_account_currency")
    private String paidFromAccountCurrency;

    @JsonProperty("paid_to")
    private String paidTo;

    @JsonProperty("paid_to_account_type")
    private String paidToAccountType;

    @JsonProperty("paid_to_account_currency")
    private String paidToAccountCurrency;

    @JsonProperty("paid_amount")
    private double paidAmount;

    @JsonProperty("paid_amount_after_tax")
    private double paidAmountAfterTax;

    @JsonProperty("source_exchange_rate")
    private double sourceExchangeRate;

    @JsonProperty("base_paid_amount")
    private double basePaidAmount;

    @JsonProperty("base_paid_amount_after_tax")
    private double basePaidAmountAfterTax;

    @JsonProperty("received_amount")
    private double receivedAmount;

    @JsonProperty("received_amount_after_tax")
    private double receivedAmountAfterTax;

    @JsonProperty("target_exchange_rate")
    private double targetExchangeRate;

    @JsonProperty("base_received_amount")
    private double baseReceivedAmount;

    @JsonProperty("base_received_amount_after_tax")
    private double baseReceivedAmountAfterTax;

    @JsonProperty("total_allocated_amount")
    private double totalAllocatedAmount;

    @JsonProperty("base_total_allocated_amount")
    private double baseTotalAllocatedAmount;

    @JsonProperty("unallocated_amount")
    private double unallocatedAmount;

    @JsonProperty("difference_amount")
    private double differenceAmount;

    @JsonProperty("apply_tax_withholding_amount")
    private int applyTaxWithholdingAmount;

    @JsonProperty("base_total_taxes_and_charges")
    private double baseTotalTaxesAndCharges;

    @JsonProperty("total_taxes_and_charges")
    private double totalTaxesAndCharges;

    @JsonProperty("reference_no")
    private String referenceNo;

    @JsonProperty("reference_date")
    private String referenceDate;

    @JsonProperty("custom_remarks")
    private int customRemarks;

    private String remarks;

    @JsonProperty("base_in_words")
    private String baseInWords;

    @JsonProperty("is_opening")
    private String isOpening;

    @JsonProperty("in_words")
    private String inWords;

    @Override
    public void update_cotnrole() {
        // Implementation for update control logic
    }

    @Override
    public void save_controle() {
        // Implementation for save control logic
    }
}
