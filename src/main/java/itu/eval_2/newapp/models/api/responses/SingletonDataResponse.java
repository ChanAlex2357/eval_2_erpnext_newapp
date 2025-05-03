package itu.eval_2.newapp.models.api.responses;

import lombok.Data;

@Data
public class SingletonDataResponse<T> {
    private T data;
}
