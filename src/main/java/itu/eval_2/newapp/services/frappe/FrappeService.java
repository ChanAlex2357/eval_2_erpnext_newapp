package itu.eval_2.newapp.services.frappe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;

@Service
public class FrappeService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;

    public String getMethodUrl(String method) {
        return apiConfig.getMethodUrl() + method;
    }

    public String getRessourceUrl(String ressource){
        return apiConfig.getRessourceUrl() + ressource;
    }
}
