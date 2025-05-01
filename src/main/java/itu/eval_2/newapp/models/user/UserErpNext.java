package itu.eval_2.newapp.models.user;

import lombok.Data;

@Data
public class UserErpNext {
    private String sid;
    private UserKeys apiKeys;
    private String username;
    private String email;
    private String fullName;

    public UserErpNext(String sid, String apiKey, String apiSecret, String username, String email, String fullName) {
        this.sid = sid;
        this.apiKeys = new UserKeys(apiKey, apiSecret);
        this.username = username;
        this.email = email;
        this.fullName = fullName;
    }

}
