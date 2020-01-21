package com.arthas557.core.entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class AbstractEntity {

    private Date createTime;
    private Date modifyTime;
}
