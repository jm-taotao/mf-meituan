package com.meituan.manage.base.response;

import lombok.*;

import java.util.function.Supplier;

/**
 * @author Jyt
 * @date 2021/9/29
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T data;

    private String code;

    private Boolean success;

    private String msg;

    /**
     * Factory method for return
     * @param func
     * @param <T>
     * @return
     */
    public static <T> Response<T> doResponse(Supplier<T> func){
        Response<T> resp = new Response<>();
        try {
            T data = func.get();
            resp.setData(data);
            resp.setCode("100000");
            resp.setMsg("");
            resp.setSuccess(true);
        }catch (Exception e){
            resp.setCode("400000");
            resp.setMsg("");
            resp.setSuccess(false);
        }
        return resp;
    }

}
