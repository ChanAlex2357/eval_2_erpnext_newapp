package itu.eval_2.newapp.builder;

import itu.eval_2.newapp.config.ApiConfig;

public class ApiUrlBuilder {

    
    private final ApiConfig apiConfig;

    public ApiUrlBuilder(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public String method(String method) {
        return apiConfig.getMethodUrl() + method;
    }

    public String ressource(String ressource){
        return apiConfig.getRessourceUrl() + ressource;
    }
    
}
