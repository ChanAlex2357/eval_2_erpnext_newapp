package itu.eval_2.newapp.models.api.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String sid;
    private String api_key;
    private String api_secret;
    private String username;
    private String full_name;
    private String email;
}
