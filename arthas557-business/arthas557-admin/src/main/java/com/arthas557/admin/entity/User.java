package com.arthas557.admin.entity;

import com.arthas557.core.entity.MysqlBaseEntity;
import lombok.Data;

@Data
public class User extends MysqlBaseEntity {

    private String userName;
    private String password;

}
