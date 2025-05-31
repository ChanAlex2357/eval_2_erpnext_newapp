package itu.eval_2.newapp.models.api.responses;

import java.util.List;

import lombok.Data;

@Data
public class ApiListResponse <T> {
    private boolean success;
    private String message;
    private List<T> data;
    private List<String> errors;
}

