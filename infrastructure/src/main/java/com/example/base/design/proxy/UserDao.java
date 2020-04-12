package com.example.base.design.proxy;

import org.springframework.stereotype.Service;

@Service
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("save");
    }
}
