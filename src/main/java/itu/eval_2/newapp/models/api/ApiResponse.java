package itu.eval_2.newapp.models.api;
    
import java.util.List;
import lombok.Data;

@Data
public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
}
