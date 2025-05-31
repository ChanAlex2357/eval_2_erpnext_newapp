package itu.eval_2.newapp.models.quotation;

import itu.eval_2.newapp.models.purchase.PurchaseBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierQuotation extends PurchaseBaseModel {

    public SupplierQuotation(){
        super("Supplier Quotation");
    }

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("valid_till")
    private String validTill;

    @JsonProperty("quotation_number")
    private String quotationNumber;

    @JsonProperty("amended_from")
    private String amendedFrom;

    @JsonProperty("cost_center")
    private String costCenter;

    private String project;

    @JsonProperty("price_list_currency")
    private String priceListCurrency;

    @JsonProperty("plc_conversion_rate")
    private double plcConversionRate;

    @JsonProperty("ignore_pricing_rule")
    private int ignorePricingRule;

    @JsonProperty("taxes_and_charges")
    private String taxesAndCharges;

    @JsonProperty("shipping_rule")
    private String shippingRule;

    private String incoterm;

    @JsonProperty("named_place")
    private String namedPlace;

    @JsonProperty("base_taxes_and_charges_added")
    private double baseTaxesAndChargesAdded;

    @JsonProperty("base_taxes_and_charges_deducted")
    private double baseTaxesAndChargesDeducted;

    @JsonProperty("base_total_taxes_and_charges")
    private double baseTotalTaxesAndCharges;

    @JsonProperty("taxes_and_charges_added")
    private double taxesAndChargesAdded;

    @JsonProperty("taxes_and_charges_deducted")
    private double taxesAndChargesDeducted;

    @JsonProperty("total_taxes_and_charges")
    private double totalTaxesAndCharges;

    @JsonProperty("apply_discount_on")
    private String applyDiscountOn;

    @JsonProperty("base_discount_amount")
    private double baseDiscountAmount;

    @JsonProperty("additional_discount_percentage")
    private double additionalDiscountPercentage;

    @JsonProperty("discount_amount")
    private double discountAmount;

    @JsonProperty("base_rounding_adjustment")
    private double baseRoundingAdjustment;

    @JsonProperty("base_rounded_total")
    private double baseRoundedTotal;

    @JsonProperty("base_in_words")
    private String baseInWords;

    @JsonProperty("rounding_adjustment")
    private double roundingAdjustment;

    @JsonProperty("rounded_total")
    private double roundedTotal;

    @JsonProperty("in_words")
    private String inWords;

    @JsonProperty("disable_rounded_total")
    private int disableRoundedTotal;

    @JsonProperty("other_charges_calculation")
    private String otherChargesCalculation;

    @JsonProperty("supplier_address")
    private String supplierAddress;

    @JsonProperty("address_display")
    private String addressDisplay;

    @JsonProperty("contact_person")
    private String contactPerson;

    @JsonProperty("contact_display")
    private String contactDisplay;

    @JsonProperty("contact_mobile")
    private String contactMobile;

    @JsonProperty("contact_email")
    private String contactEmail;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_address_display")
    private String shippingAddressDisplay;

    @JsonProperty("billing_address")
    private String billingAddress;

    @JsonProperty("billing_address_display")
    private String billingAddressDisplay;

    @JsonProperty("tc_name")
    private String tcName;

    private String terms;

    @JsonProperty("letter_head")
    private String letterHead;

    @JsonProperty("group_same_items")
    private int groupSameItems;

    @JsonProperty("select_print_heading")
    private String selectPrintHeading;

    private String language;

    @JsonProperty("auto_repeat")
    private String autoRepeat;

    @JsonProperty("is_subcontracted")
    private int isSubcontracted;

    private String opportunity;

    private SupplierQuotationItem[] items;

    @Override
    public void update_cotnrole() {
        for ( SupplierQuotationItem item : items) {
            item.update_cotnrole();
        }
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
        
    }
}
