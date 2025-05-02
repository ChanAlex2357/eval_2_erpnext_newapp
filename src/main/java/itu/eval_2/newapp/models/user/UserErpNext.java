package itu.eval_2.newapp.models.user;

import lombok.Data;

@Data
public class UserErpNext {
    private String sid;
    private UserKeys keys;
    private String username;
    private String email;
    private String fullName;

    public UserErpNext(String sid, String apiKey, String apiSecret, 
                     String username, String email, String fullName) {
        this.sid = sid;
        this.keys = new UserKeys(apiKey, apiSecret);
        this.username = username;
        this.email = email;
        this.fullName = fullName;

    }

    public boolean isAuthenticated() {
        return keys != null && keys.isValid();
    }

    public String getAuthToken() {
        if (!isAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }
        return "token " + getKeys().getAuthorizationKeys();
    }
}