package itu.eval_2.newapp.models.api.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.models.api.requests.RequestModel;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import lombok.Data;

@Data
public class ApiRequestWrapper<R extends RequestModel ,S extends ResponseModel> {
    private R requestModel;
    private S responseModel;
    private String endpoint;
    private HttpHeaders headers;
    private HttpMethod method;
    
    @Autowired
    private RestTemplate restTemplate;

    public ApiRequestWrapper(HttpHeaders headers,HttpMethod method,String endpoint,R request, S response) {
        this.requestModel = request;
        this.responseModel = response;
        this.endpoint = endpoint;
    }

    public ResponseEntity<ApiResponseWrapper<S>> call() {
        HttpEntity<R> requestEntity = new HttpEntity<>(requestModel, headers);
        return restTemplate.exchange(endpoint, method, requestEntity, new ParameterizedTypeReference<ApiResponseWrapper<S>>() {});
    }
}
