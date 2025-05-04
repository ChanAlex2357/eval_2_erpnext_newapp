package itu.eval_2.newapp.models.supplier;

import itu.eval_2.newapp.annotations.date.ErpNextDateTime;
import itu.eval_2.newapp.models.action.FrappeDocument;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErpNextSupplier extends FrappeDocument{

    public ErpNextSupplier(){
        super("Supplier");
    }

    @JsonProperty("naming_series")
    private String namingSeries;

    @JsonProperty("supplier_name")
    private String supplierName;

    private String country;

    @JsonProperty("supplier_group")
    private String supplierGroup;

    @JsonProperty("supplier_type")
    private String supplierType;

    @JsonProperty("is_transporter")
    private boolean isTransporter;

    private String image;

    @JsonProperty("default_currency")
    private String defaultCurrency;

    @JsonProperty("default_bank_account")
    private String defaultBankAccount;

    @JsonProperty("default_price_list")
    private String defaultPriceList;

    @JsonProperty("is_internal_supplier")
    private boolean isInternalSupplier;

    @JsonProperty("represents_company")
    private String representsCompany;

    @JsonProperty("supplier_details")
    private String supplierDetails;

    private String website;
    private String language;

    @JsonProperty("tax_id")
    private String taxId;

    @JsonProperty("tax_category")
    private String taxCategory;

    @JsonProperty("tax_withholding_category")
    private String taxWithholdingCategory;

    @JsonProperty("supplier_primary_address")
    private String supplierPrimaryAddress;

    @JsonProperty("primary_address")
    private String primaryAddress;

    @JsonProperty("supplier_primary_contact")
    private String supplierPrimaryContact;

    @JsonProperty("mobile_no")
    private String mobileNo;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("payment_terms")
    private String paymentTerms;

    @JsonProperty("allow_purchase_invoice_creation_without_purchase_order")
    private boolean allowPurchaseInvoiceCreationWithoutPurchaseOrder;

    @JsonProperty("allow_purchase_invoice_creation_without_purchase_receipt")
    private boolean allowPurchaseInvoiceCreationWithoutPurchaseReceipt;

    @JsonProperty("is_frozen")
    private boolean isFrozen;

    private boolean disabled;

    @JsonProperty("warn_rfqs")
    private boolean warnRfqs;

    @JsonProperty("warn_pos")
    private boolean warnPos;

    @JsonProperty("prevent_rfqs")
    private boolean preventRfqs;

    @JsonProperty("prevent_pos")
    private boolean preventPos;

    @JsonProperty("on_hold")
    private boolean onHold;

    @JsonProperty("hold_type")
    private String holdType;

    @ErpNextDateTime
    @JsonProperty("release_date")
    private LocalDateTime releaseDate;

    @JsonProperty("_user_tags")
    private String userTags;

    @JsonProperty("_comments")
    private String comments;

    @JsonProperty("_assign")
    private String assign;

    @JsonProperty("_liked_by")
    private String likedBy;

    @Override
    public void update_cotnrole() {}
    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
        
    }

}
