package itu.eval_2.newapp.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String getRessourceUrl(String doctype,String[] fields){
        String uri = baseUrl + ressource +"/"+ doctype;

        String fieldsStr = makeResourceFields(fields); 
        
        uri = UriComponentsBuilder.fromUriString(uri)
                .queryParam("fields", fieldsStr)  // Get all fields
                .build().toUriString();

        return uri;
    }
    private String makeResourceFields(String[] fields){
        if (fields == null || fields.length == 0) {
            return "";
        }

        String fieldsStr = "fields=[";

        String suffix = ",";
        for (int i = 0; i < fields.length; i++) {
            String f = fields[i];
            if (i == fields.length - 1) {
                suffix = "";
            }
            fieldsStr += "\"" + f + "\""+suffix;
        }

        fieldsStr = "]";
        return fieldsStr;
    }

    public String getRessourceUrl(String doctype) {
        return getRessourceUrl(doctype, null);
    }

    public String getResourceWithAllFieldsUrl(String doctype){
        String[] fields = new String[]{"*"};
        return getRessourceUrl(doctype, fields);
    }


}