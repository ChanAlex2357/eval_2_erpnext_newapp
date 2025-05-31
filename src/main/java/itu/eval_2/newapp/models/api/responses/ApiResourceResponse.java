package itu.eval_2.newapp.models.api.responses;

import java.util.List;

import lombok.Data;

@Data
public class ApiResourceResponse<T> {
    private List<T> data;
    private String message;
    private String exc_type;
    private boolean success;
}
