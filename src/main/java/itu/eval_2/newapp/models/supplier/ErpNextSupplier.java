package itu.eval_2.newapp.models.supplier;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErpNextSupplier {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creation;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;
    private String modifiedBy;
    private String owner;
    private int docstatus;
    private int idx;
    private String namingSeries;
    private String supplierName;
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
    private LocalDateTime releaseDate;
    private String userTags;
    private String comments;
    private String assign;
    private String likedBy;

}
