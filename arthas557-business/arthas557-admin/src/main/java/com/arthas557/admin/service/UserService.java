package com.arthas557.admin.service;

import com.arthas557.admin.dao.UserMapper;
import com.arthas557.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(User user){
        userMapper.insert(user);
    }


    @Transactional
    public void insertException(User user){
        throw new IllegalArgumentException("保存出差");
    }
}
