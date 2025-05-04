package itu.eval_2.newapp.models.purchase;

import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.payment.PaymentEntryReference;
import itu.eval_2.newapp.models.payment.PaymentEntryReferenceable;
import itu.eval_2.newapp.models.payment.PaymentSchedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInvoice extends PurchaseBaseModel implements PaymentEntryReferenceable {

    public PurchaseInvoice(){
        super("Purchase Invoice");
    }

    @JsonProperty("posting_date")
    private String postingDate;

    @JsonProperty("posting_time")
    private String postingTime;

    @JsonProperty("set_posting_time")
    private int setPostingTime;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("is_paid")
    private int isPaid;

    @JsonProperty("is_return")
    private int isReturn;


    @JsonProperty("buying_price_list")
    private String buyingPriceList;

    @JsonProperty("price_list_currency")
    private String priceListCurrency;

    @JsonProperty("plc_conversion_rate")
    private double plcConversionRate;

    @JsonProperty("in_words")
    private String inWords;

    private List<PurchaseInvoiceItem> items;

    @JsonProperty("payment_schedule")
    private List<PaymentSchedule> paymentSchedule;

    @Override
    public void update_cotnrole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cotnrole'");
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PaymentEntryReference getAsPaymentEntryReference() {
        PaymentEntryReference ref = new PaymentEntryReference();
        ref.setReferenceDoctype(getDoctype());
        ref.setReferenceName(getName());
        ref.setDueDate(getDueDate());
        // Amount 
        ref.setTotalAmount(getGrandTotal());
        ref.setOutstandingAmount(getGrandTotal());
        ref.setAllocatedAmount(getGrandTotal());

        ref.setExchangeRate(1);
        ref.setAccount(getCrediTo());

        return ref;
    }

    @Override
    public void setPaymenteEntry(PaymentEntry paymentEntry) {
        // Paid to config

    }
}
