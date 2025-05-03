package itu.eval_2.newapp.config;

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

    private String makeRessourceFiters(FrappApiFilter[] filters) {
        String filtersStr = "[";
 
        String suffix = ",";
 
         for (int i = 0; i < filters.length; i++) {
             if (i == filters.length - 1) {
                 suffix = "";
             }
             filtersStr += filters[i].getFilterStr()+suffix;    
        }
 
        filtersStr += "]";
        return filtersStr;
     }
 
     private String makeResourceFields(String[] fields){
         String fieldsStr = "[";
 
         String suffix = ",";
         for (int i = 0; i < fields.length; i++) {
             String f = fields[i];
             if (i == fields.length - 1) {
                 suffix = "";
             }
             fieldsStr += "\"" + f + "\""+suffix;
         }
 
         fieldsStr += "]";
         return fieldsStr;
     }
 
    public String getRessourceUrl(String doctype,String[] fields, FrappApiFilter[] filters ){
        String uri = baseUrl + ressource +"/"+ doctype;

        String fieldsStr = makeResourceFields(fields); 
        String filterSrt = makeRessourceFiters(filters);        
        uri = UriComponentsBuilder.fromUriString(uri)
                .queryParam("fields", fieldsStr)  // Get all fields
                .queryParam("filters", filterSrt)
                .build().toUriString();

        return uri;
    }

    public String getRessourceUrl(String doctype) {
        return getRessourceUrl(doctype, null,null);
    }

    public String getResourceWithAllFieldsUrl(String doctype,FrappApiFilter[] filters){
        String[] fields = new String[]{"*"};
        return getRessourceUrl(doctype, fields,filters);
    }
    
    public String getResourceWithAllFieldsUrl(String doctype){
        String[] fields = new String[]{"*"};
        return getRessourceUrl(doctype, fields,null);
    }
}