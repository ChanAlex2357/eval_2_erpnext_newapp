package itu.eval_2.newapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String baseUrl;
    private String loginEndpoint;
    private String method;
    private String ressource;
    private int timeout;

    public String getRessourceUrl() {
        return baseUrl + ressource;
    }

    public String getMethodUrl() {
        return baseUrl + method;
    }

    public String getMethodUrl(String url) {
        return baseUrl + method + url;
    }

    public String getRessourceUrl(String url) {
        return baseUrl + ressource + url;
    }

}