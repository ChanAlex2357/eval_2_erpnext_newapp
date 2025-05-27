package itu.eval_2.newapp.exceptions;

import itu.eval_2.newapp.config.ApiConfig;

public class AuthenticationException extends  Exception{

    public  AuthenticationException(){
        super("Your are not logged in. Please log in at http://localhost:8080/auth/login");
    }

}
