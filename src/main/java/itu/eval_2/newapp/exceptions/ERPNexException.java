package itu.eval_2.newapp.exceptions;

import org.springframework.http.ResponseEntity;

import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class ERPNexException extends Exception {
    private ApiResponse<String> apiResponse;
    public ResponseEntity<String> response;
    public ErpNextCallException callException;

    public ERPNexException(String message , Throwable e){
        super(message, e);
    }
    public ERPNexException(String message){
        super(message);
    }

    public ERPNexException(ErpNextCallException callException, ResponseEntity<String> response, Throwable e){
        super(callException.getMessage(),e);
        setResponse(response);
        setCallException(callException);
    }


    public ApiResponse<String> getAsApiResponse(){
        if(this.apiResponse == null) {
            buildApiResponse();
        }
        return  this.apiResponse;
    }

    public Map<String, Object> getLogMap(){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("detailed_response",response);
        if (callException != null) {
            errorBody.putAll(callException.getLogMap());
        }
        return errorBody;
    }
    private void buildApiResponse(){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setErrors(this.getLogMap());
        apiResponse.setMessage(this.getMessage());
        this.apiResponse = apiResponse;
    }
}
