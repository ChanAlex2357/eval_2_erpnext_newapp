package itu.eval_2.newapp.models.api;

import lombok.Data;

@Data
public class ApiResponseWrapper<T> {
    private ApiResponse<T> message;
}
