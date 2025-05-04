package itu.eval_2.newapp.models.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.payment.PaymentSchedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInvoice extends PurchaseBaseModel {

    public PurchaseInvoice({
        super("Purchase Invoice");
    })

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
}
