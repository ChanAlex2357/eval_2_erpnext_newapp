package itu.eval_2.newapp.models.item;

import itu.eval_2.newapp.annotations.date.ErpNextDateTime;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Data
public class MaterialRequest {
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

    @JsonProperty("naming_series")
    private String namingSeries;

    private String title;

    @JsonProperty("material_request_type")
    private String materialRequestType;

    private String customer;
    private String company;

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("schedule_date")
    private String scheduleDate;

    @JsonProperty("amended_from")
    private String amendedFrom;

    @JsonProperty("scan_barcode")
    private String scanBarcode;

    @JsonProperty("set_from_warehouse")
    private String setFromWarehouse;

    @JsonProperty("set_warehouse")
    private String setWarehouse;

    @JsonProperty("tc_name")
    private String tcName;

    private String terms;
    private String status;

    @JsonProperty("per_ordered")
    private int perOrdered;

    @JsonProperty("transfer_status")
    private String transferStatus;

    @JsonProperty("per_received")
    private int perReceived;

    @JsonProperty("letter_head")
    private String letterHead;

    @JsonProperty("select_print_heading")
    private String selectPrintHeading;

    @JsonProperty("job_card")
    private String jobCard;

    @JsonProperty("work_order")
    private String workOrder;

}
