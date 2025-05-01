package itu.eval_2.newapp.models.api.responses;
    
import java.util.List;
import lombok.Data;

@Data
public class ApiResponse<T extends ResponseModel> {
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
}
