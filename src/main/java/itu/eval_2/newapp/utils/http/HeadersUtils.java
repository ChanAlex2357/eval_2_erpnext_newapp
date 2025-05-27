package itu.eval_2.newapp.utils.http;

import java.util.Collections;

import itu.eval_2.newapp.exceptions.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import itu.eval_2.newapp.models.user.UserErpNext;

public class HeadersUtils {
    public static HttpHeaders createHeaders(UserErpNext user) throws AuthenticationException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
            headers.set("Authorization", user.getAuthToken());
        }
        catch (RuntimeException e){
            if (user == null) {
                throw new AuthenticationException();
            }
            throw  e;
        }
        return headers;
    }
}
