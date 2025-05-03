package itu.eval_2.newapp.utils.http;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import itu.eval_2.newapp.models.user.UserErpNext;

public class HeadersUtils {
    public static HttpHeaders createHeaders(UserErpNext user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", user.getAuthToken());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
