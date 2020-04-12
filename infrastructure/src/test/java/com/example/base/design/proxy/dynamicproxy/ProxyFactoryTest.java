package com.example.base.design.proxy.dynamicproxy;


import com.alibaba.fastjson.JSON;
import com.example.base.design.proxy.IUserDao;
import com.example.base.design.proxy.UserDao;
import com.example.base.domain.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ProxyFactoryTest {

    @Test
    public void proxy()  {
        LoggerFactory.getLogger(this.getClass());
        IUserDao userDao = new UserDao();
        IUserDao proxy= (IUserDao)new ProxyFactory(userDao).getInstance();
        proxy.save();
        System.out.println(proxy.getClass());
    }
    @Test
    public void proxy2() throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        userInfo.setUserName("");


        System.out.println(JSON.toJSONString(userInfo));;

        String json="{\"userId\":0,\"userName\":null}";

        UserInfo o = JSON.parseObject(json, UserInfo.class);


        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo1 = objectMapper.readValue(json, UserInfo.class);
        System.out.println(JSON.toJSONString(o)+"\r\n"+objectMapper.writeValueAsString(userInfo1));


        String a="8.3.1";
        String b="8.16.0";
        System.out.println(a.compareTo(b));

    }
}