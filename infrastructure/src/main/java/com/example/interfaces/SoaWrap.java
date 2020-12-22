package com.example.interfaces;

import com.example.base.json.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.lang.reflect.Field;
import java.util.List;

/**
 * @author bl_xu
 * @date 2018-06-07
 */
public final class SoaWrap {
    private SoaWrap() {

    }


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public interface ISoaCaller<R> {
        /**
         * 调用目标函数
         *
         * @return 目标函数返回值
         * @throws Exception 异常
         */
        R call() throws Exception;//NOSONAR


    }

    public interface ISoaCallerV2<T, R> {
        /**
         * 调用目标函数
         *
         * @return 目标函数返回值
         * @throws Exception 异常
         */
        R call(T t) throws Exception;//NOSONAR


    }

    public static <T, R> R callV2(T request,
                                  ISoaCallerV2<T, R> f) throws Exception {
        try {
            System.out.println("request:"+JsonUtil.toString(request));
            R response = f.call(request);
            System.out.println("response:"+JsonUtil.toString(response));
            return response;

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }


    public static <T, R> R call(
            ISoaCaller<R> f,
            T request) throws Exception {
        try {
            System.out.println(JsonUtil.toString(request));
            R response = f.call();
            System.out.println(JsonUtil.toString(response));
            return response;

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }


}