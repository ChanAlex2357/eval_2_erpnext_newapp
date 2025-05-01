package itu.eval_2.newapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private ApiConfig apiConfig;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
            = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(apiConfig.getTimeout());
        clientHttpRequestFactory.setReadTimeout(apiConfig.getTimeout());
        return new RestTemplate(clientHttpRequestFactory);
    }
}