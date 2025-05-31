package itu.eval_2.newapp.models.api.responses;
    
import java.util.List;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success = true;
    private String message;
    private T data;
    private String redirection;
    private Object errors;
}
