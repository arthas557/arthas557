package com.arthas557.admin.service;

import com.arthas557.admin.dao.LogMapper;
import com.arthas557.admin.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(Log log){
        logMapper.insert(log);
    }
}
