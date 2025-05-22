package itu.eval_2.newapp.models.crm;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;

@Data
public class Leads extends FrappeDocument {
    private String salutation;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("lead_name")
    private String leadName;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("email_id")
    private  String emailId;

    @JsonProperty("phone")
    private  String phone;
//    "gender": "Female",
//    "lead_owner": "Administrator",
//    "status": "Converted",
//    "type": "",
//    "request_type": "",
//            "mobile_no": "0384178406",
//            "whatsapp_no": "0384178406",
//            "no_of_employees": "1-10",
//            "annual_revenue": 0.0,
//            "country": "Madagascar",
//            "qualification_status": "Unqualified",
//            "company": "Itu Eval (Demo)",
//            "language": "en",
//            "image": "",
//            "title": "Madam Misa Lalaina",
//            "disabled": 0,
//            "unsubscribed": 0,
//            "blog_subscriber": 0,
//            "doctype": "Lead",
//            "notes": []
    public Leads() {
        super("Lead");
    }

    @Override
    public void update_cotnrole() {

    }

    @Override
    public void save_controle() {

    }
}
