package com.example.base.design.proxy.dynamicproxy.factorybean;


import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientInvokerProxy {
    Map<Method, Class<?>> invokers;

    public ClientInvokerProxy() {
        super();
        this.invokers = new ConcurrentHashMap<>();
    }

    public Object invoke(Method method, Object[] args) {

        Object request = args[0];

        try {
            return method.invoke(args);
        } catch (Exception e) {
            e.printStackTrace();
        }


        throw new IllegalArgumentException();
    }
}
