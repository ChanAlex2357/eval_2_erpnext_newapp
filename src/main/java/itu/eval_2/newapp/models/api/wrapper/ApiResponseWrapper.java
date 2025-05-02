package itu.eval_2.newapp.models.api.wrapper;

import java.util.List;

import itu.eval_2.newapp.models.api.responses.ApiResponse;
import lombok.Data;

@Data
public class ApiResponseWrapper<T> {
    private ApiResponse<T> message;


    public T getData(){
        return getMessage().getData();
    }

    public boolean isSuccess(){
        return getMessage().isSuccess();
    }
    
    public List<String> errors(){
        return getMessage().getErrors();
    }
}
