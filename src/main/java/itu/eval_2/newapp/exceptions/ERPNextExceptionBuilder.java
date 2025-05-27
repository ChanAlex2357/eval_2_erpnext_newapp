package itu.eval_2.newapp.exceptions;

public class ERPNextExceptionBuilder {
    public  static  ERPNexException handle(ErpNextCallException e){
        String excption_message = e.getMessage();

        // Check for Unothorized 401
        if (excption_message.contains("Unauthorized")){
            return buildUnauthorizedException(e);
        }

        return new ERPNexException("Error while web service call : "+e.getMessage(),e.getCause());
    }


    public static  ERPNexException buildUnauthorizedException(ErpNextCallException e){
        return  new ERPNexException("Your have not sufficient authorisation to access the api at "+e.getUrl(),e.getCause());
    }

}
