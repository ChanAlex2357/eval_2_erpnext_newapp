package itu.eval_2.newapp.models.api;

import java.net.http.HttpResponse;

import itu.eval_2.newapp.models.api.requests.RequestModel;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import lombok.Data;

@Data
public class FrappeApi {
    RequestModel requestModel;
    ResponseModel responseModel;
    HttpResponse<ApiResponseWrapper<? extends RequestModel>> response;
    private FrappeApi(RequestModel requestModel,ResponseModel responseModel){
        setRequestModel(requestModel);
        setRequestModel(requestModel);
    }

    public ApiResponseWrapper<? extends ResponseModel> getResponseBody(){
        return getResponse().body();
    }
}
