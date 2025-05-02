package itu.eval_2.newapp.models.api.responses;

import itu.eval_2.newapp.models.user.UserErpNext;
import lombok.Data;

@Data
public class LoginResponse implements ResponseModel {
    private String sid;
    private String api_key;
    private String api_secret;
    private String username;
    private String full_name;
    private String email;

    public UserErpNext getUser() {
        return new UserErpNext(sid, api_key, api_secret, username, email, full_name);
    }
}
