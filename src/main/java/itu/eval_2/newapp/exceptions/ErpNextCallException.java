package itu.eval_2.newapp.exceptions;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class ErpNextCallException extends Exception{
    private final String url;
    private final HttpMethod method;

    public ErpNextCallException( String url, HttpMethod method, String message, Throwable cause){
        super(message, cause);
        this.url = url;
        this.method = method;
    }
    public ErpNextCallException( String url, HttpMethod method, Throwable cause){
        this(url, method, cause.getMessage(), cause);
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, Object> getLogMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("url", getUrl());
        map.put("method", getMethod().name());

        return  map;
    }

}
