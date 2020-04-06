package com.example.base.design.proxy.dynamicproxy.factorybean;



import com.example.base.ServiceInitializer;
import com.example.base.design.proxy.IUserDao;
import com.example.base.design.proxy.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceInitializer.class)
public class ProxyFacadeByCglibTest {

    @Resource
    private UserDao userDao;

    @Test
    public void getObject() throws Exception {
        UserDao object = (UserDao)new ProxyFacadeByCglib(userDao.getClass()).getObject();
        object.save();
    }
}