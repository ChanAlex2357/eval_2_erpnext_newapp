package itu.eval_2.newapp.models.api;


import itu.eval_2.newapp.models.api.requests.RequestModel;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import lombok.Data;

@Data
public class FrappeApi {
    RequestModel requestModel;
    ApiResponseWrapper responseModel;
    
    public FrappeApi(RequestModel requestModel){
        setRequestModel(requestModel);
    }
}
