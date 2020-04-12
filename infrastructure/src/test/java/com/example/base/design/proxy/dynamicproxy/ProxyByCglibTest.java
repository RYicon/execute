package com.example.base.design.proxy.dynamicproxy;

import com.example.base.design.proxy.UserDao;
import org.junit.Test;

//save也不想去调用
public class ProxyByCglibTest {
    @Test
    public void proxy() {

        UserDao userDao = new UserDao();

        UserDao proxyInstance = (UserDao) new ProxyByCglib(userDao).getProxyInstance();
        proxyInstance.save();
        System.out.println(proxyInstance.getClass());
    }
}