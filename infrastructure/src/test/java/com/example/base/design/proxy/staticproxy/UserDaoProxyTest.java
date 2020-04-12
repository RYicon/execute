package com.example.base.design.proxy.staticproxy;


import com.example.base.design.proxy.UserDao;
import org.junit.Test;


public class UserDaoProxyTest {
    @Test
    public void save_proxy() {
        UserDaoProxy userDaoProxy = new UserDaoProxy(new UserDao());
        userDaoProxy.save();
    }
}
