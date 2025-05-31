package itu.eval_2.newapp.models.api.responses;

public class MethodApiResponse <T>{
    public T message;
    public T getMessage() {
        return message;
    }
    public void setMessage(T message) {
        this.message = message;
    }
}
