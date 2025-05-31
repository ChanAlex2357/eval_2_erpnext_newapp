package itu.eval_2.newapp.exceptions;

import org.springframework.http.ResponseEntity;

import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ERPNextIntegrationException extends Exception {
    public ApiResponse<?> error_data;
    public ApiResponseWrapper<String> error_message_data;
    public ResponseEntity<String> response;
    public ERPNextIntegrationException(String message , Throwable e){
        super(message, e);
    }
    public ERPNextIntegrationException(String message){
        super(message);
    }

    public ERPNextIntegrationException(String message, ResponseEntity<String> response){
        super(message);
        setResponse(response);
    }

}
