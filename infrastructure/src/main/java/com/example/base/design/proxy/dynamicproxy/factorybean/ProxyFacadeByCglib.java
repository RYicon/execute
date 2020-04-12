package com.example.base.design.proxy.dynamicproxy.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import javax.annotation.Resource;
import java.lang.reflect.Method;

public class ProxyFacadeByCglib<T> implements FactoryBean {
    private Class<T> target;

    public ProxyFacadeByCglib(Class<T> target) {
        this.target = target;
    }

    @Resource
    private ClientInvokerProxy clientInvokerProxy;

    public T getObject() throws Exception {
        //增强
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("开启事务");
                // 执行目标对象的方法
//                clientInvokerProxy.invoke(method, objects);
                Object returnValue = method.invoke(target, objects);
                System.out.println("关闭事务");
                return returnValue;
            }
        });
        return (T) en.create();
    }


    public Class<?> getObjectType() {
        return target;
    }

    public boolean isSingleton() {
        return true;
    }
}
