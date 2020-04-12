package com.example.base.design.proxy.staticproxy;


import com.example.base.design.proxy.IUserDao;

public class UserDaoProxy implements IUserDao {
    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("start tran");
        userDao.save();
        System.out.println("end tran");
    }
}
