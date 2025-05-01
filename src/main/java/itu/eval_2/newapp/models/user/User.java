package itu.eval_2.newapp.models.user;
import lombok.Data;

@Data
public class User {
    private String sid;
    private UserKeys apiKeys;
    private String username;
    private String email;
}
