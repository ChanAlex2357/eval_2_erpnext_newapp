package itu.eval_2.newapp.models.quotation;

import itu.eval_2.newapp.models.action.FrappeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierQuotation extends FrappeModel {
    @JsonProperty("naming_series")
    private String namingSeries;

    private String supplier;

    @JsonProperty("supplier_name")
    private String supplierName;

    private String company;
    private String status;

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
    private String currency;

    @JsonProperty("conversion_rate")
    private double conversionRate;

    @JsonProperty("buying_price_list")
    private String buyingPriceList;

    @JsonProperty("price_list_currency")
    private String priceListCurrency;

    @JsonProperty("plc_conversion_rate")
    private double plcConversionRate;

    @JsonProperty("ignore_pricing_rule")
    private int ignorePricingRule;

    @JsonProperty("total_qty")
    private int totalQty;

    @JsonProperty("total_net_weight")
    private double totalNetWeight;

    @JsonProperty("base_total")
    private double baseTotal;

    @JsonProperty("base_net_total")
    private double baseNetTotal;

    private double total;

    @JsonProperty("net_total")
    private double netTotal;

    @JsonProperty("tax_category")
    private String taxCategory;

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

    @JsonProperty("base_grand_total")
    private double baseGrandTotal;

    @JsonProperty("base_rounding_adjustment")
    private double baseRoundingAdjustment;

    @JsonProperty("base_rounded_total")
    private double baseRoundedTotal;

    @JsonProperty("base_in_words")
    private String baseInWords;

    @JsonProperty("grand_total")
    private double grandTotal;

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
    public void cotnrole() {
        for ( SupplierQuotationItem item : items) {
            item.cotnrole();
        }
    }
}
