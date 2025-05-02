package itu.eval_2.newapp.models.supplier;

import itu.eval_2.newapp.annotations.date.ErpNextDateTime;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErpNextSupplier {
    private String name;

    @ErpNextDateTime
    private LocalDateTime creation;
    
    @ErpNextDateTime
    private LocalDateTime modified;

    private String modifiedBy;
    private String owner;
    private int docstatus;
    private int idx;
    private String namingSeries;
    private String supplier_name;
    private String country;
    private String supplierGroup;
    private String supplierType;
    private boolean isTransporter;
    private String image;
    private String defaultCurrency;
    private String defaultBankAccount;
    private String defaultPriceList;
    private boolean isInternalSupplier;
    private String representsCompany;
    private String supplierDetails;
    private String website;
    private String language;
    private String taxId;
    private String taxCategory;
    private String taxWithholdingCategory;
    private String supplierPrimaryAddress;
    private String primaryAddress;
    private String supplierPrimaryContact;
    private String mobileNo;
    private String emailId;
    private String paymentTerms;
    private boolean allowPurchaseInvoiceCreationWithoutPurchaseOrder;
    private boolean allowPurchaseInvoiceCreationWithoutPurchaseReceipt;
    private boolean isFrozen;
    private boolean disabled;
    private boolean warnRfqs;
    private boolean warnPos;
    private boolean preventRfqs;
    private boolean preventPos;
    private boolean onHold;
    private String holdType;

    @ErpNextDateTime
    private LocalDateTime releaseDate;

    private String userTags;
    private String comments;
    private String assign;
    private String likedBy;

}
