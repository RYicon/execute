package com.example.base.design.proxy.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class ProxyByCglib {
    private Object target;
    public ProxyByCglib(Object target){
        this.target=target;
    }
    public Object getProxyInstance(){
        //增强
        Enhancer en =new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("开启事务");
                // 执行目标对象的方法
                Object returnValue = method.invoke(target, objects);
                System.out.println("关闭事务");
                return null;
            }
        });
        return en.create();

    }

}
