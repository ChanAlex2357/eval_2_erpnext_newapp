package itu.eval_2.newapp.models.purchase;

import itu.eval_2.newapp.annotations.date.ErpNextDateTime;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Data
public class PurchaseOrder {
    private String name;
    private String owner;

    @ErpNextDateTime
    private LocalDateTime creation;

    @ErpNextDateTime
    private LocalDateTime modified;

    @JsonProperty("modified_by")
    private String modifiedBy;

    private int docstatus;
    private int idx;
    private String title;

    @JsonProperty("naming_series")
    private String namingSeries;

    private String supplier;

    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("order_confirmation_no")
    private String orderConfirmationNo;

    @JsonProperty("order_confirmation_date")
    private String orderConfirmationDate;

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("schedule_date")
    private String scheduleDate;

    private String company;

    @JsonProperty("apply_tds")
    private int applyTds;

    @JsonProperty("tax_withholding_category")
    private String taxWithholdingCategory;

    @JsonProperty("is_subcontracted")
    private int isSubcontracted;

    @JsonProperty("supplier_warehouse")
    private String supplierWarehouse;

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

    @JsonProperty("scan_barcode")
    private String scanBarcode;

    @JsonProperty("set_from_warehouse")
    private String setFromWarehouse;

    @JsonProperty("set_warehouse")
    private String setWarehouse;

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

    @JsonProperty("tax_withholding_net_total")
    private double taxWithholdingNetTotal;

    @JsonProperty("base_tax_withholding_net_total")
    private double baseTaxWithholdingNetTotal;

    @JsonProperty("set_reserve_warehouse")
    private String setReserveWarehouse;

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

    @JsonProperty("base_grand_total")
    private double baseGrandTotal;

    @JsonProperty("base_rounding_adjustment")
    private double baseRoundingAdjustment;

    @JsonProperty("base_in_words")
    private String baseInWords;

    @JsonProperty("base_rounded_total")
    private double baseRoundedTotal;

    @JsonProperty("grand_total")
    private double grandTotal;

    @JsonProperty("rounding_adjustment")
    private double roundingAdjustment;

    @JsonProperty("rounded_total")
    private double roundedTotal;

    @JsonProperty("disable_rounded_total")
    private int disableRoundedTotal;

    @JsonProperty("in_words")
    private String inWords;

    @JsonProperty("advance_paid")
    private double advancePaid;

    @JsonProperty("apply_discount_on")
    private String applyDiscountOn;

    @JsonProperty("base_discount_amount")
    private double baseDiscountAmount;

    @JsonProperty("additional_discount_percentage")
    private double additionalDiscountPercentage;

    @JsonProperty("discount_amount")
    private double discountAmount;

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

    private String customer;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_contact_person")
    private String customerContactPerson;

    @JsonProperty("customer_contact_display")
    private String customerContactDisplay;

    @JsonProperty("customer_contact_mobile")
    private String customerContactMobile;

    @JsonProperty("customer_contact_email")
    private String customerContactEmail;

    @JsonProperty("payment_terms_template")
    private String paymentTermsTemplate;

    @JsonProperty("tc_name")
    private String tcName;

    private String terms;
    private String status;

    @JsonProperty("advance_payment_status")
    private String advancePaymentStatus;

    @JsonProperty("per_billed")
    private int perBilled;

    @JsonProperty("per_received")
    private int perReceived;

    @JsonProperty("letter_head")
    private String letterHead;

    @JsonProperty("group_same_items")
    private int groupSameItems;

    @JsonProperty("select_print_heading")
    private String selectPrintHeading;

    private String language;

    @JsonProperty("from_date")
    private String fromDate;

    @JsonProperty("to_date")
    private String toDate;

    @JsonProperty("auto_repeat")
    private String autoRepeat;

    @JsonProperty("is_internal_supplier")
    private int isInternalSupplier;

    @JsonProperty("represents_company")
    private String representsCompany;

    @JsonProperty("ref_sq")
    private String refSq;

    @JsonProperty("party_account_currency")
    private String partyAccountCurrency;

    @JsonProperty("inter_company_order_reference")
    private String interCompanyOrderReference;

    @JsonProperty("is_old_subcontracting_flow")
    private int isOldSubcontractingFlow;

    // Getters and Setters
    // ...
}
