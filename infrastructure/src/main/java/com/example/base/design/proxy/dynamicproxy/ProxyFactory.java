package com.example.base.design.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //为对象生成代理对象
    public Object getInstance() {
        Class<?> classes = target.getClass();

        return Proxy.newProxyInstance(classes.getClassLoader(), classes.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("start tran");
                Object invoke = method.invoke(target, args);
                System.out.println("end tran");

                return null;
            }
        });
    }
}
