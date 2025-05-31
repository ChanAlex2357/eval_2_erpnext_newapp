package itu.eval_2.newapp.models.api.responses;

import java.util.List;

import lombok.Data;

@Data
public class ApiListResponse <T> {
    private boolean success = true;
    private String message;
    private List<T> data;
    private List<Object> errors;

    public ApiListResponse(boolean status, String msg, List<T> data, List<Object> errors){
        this.success = status;
        this.message = msg;
        this.data = data;
        this.errors = errors;
    }
}

