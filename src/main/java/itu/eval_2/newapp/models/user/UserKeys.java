package itu.eval_2.newapp.models.user;

import lombok.Data;

@Data
public class UserKeys {
    private String apiKey;
    private String apiSecret;
    public UserKeys(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
}
