package itu.eval_2.newapp.models.api.wrapper;

import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import lombok.Data;

@Data
public class ApiResponseWrapper<T extends ResponseModel> {
    private ApiResponse<T> message;
}
