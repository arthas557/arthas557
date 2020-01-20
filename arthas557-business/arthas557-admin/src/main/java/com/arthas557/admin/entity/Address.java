package com.arthas557.admin.entity;

import com.arthas557.core.entity.MysqlBaseEntity;
import lombok.Data;

@Data
public class Address extends MysqlBaseEntity {

    private Integer lineNumber;

    private String preName;

    private String currName;

    private String address;
}
