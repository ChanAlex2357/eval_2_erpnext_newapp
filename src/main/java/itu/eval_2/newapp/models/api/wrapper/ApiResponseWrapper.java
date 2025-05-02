package itu.eval_2.newapp.models.api.wrapper;

import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import lombok.Data;

@Data
public class ApiResponseWrapper<T extends ResponseModel> {
    private ApiResponse<T> message;

    public ApiResponseWrapper(T model) {
        this.message = new ApiResponse<>();
        this.message.setData(model);
    }

    public T getData() {
        return message.getData();
    }
}
