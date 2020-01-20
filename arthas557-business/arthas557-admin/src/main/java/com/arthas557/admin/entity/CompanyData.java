package com.arthas557.admin.entity;

import com.arthas557.core.entity.MysqlBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("company_data")
@Data
public class CompanyData extends MysqlBaseEntity {

    private Integer lineNumber;
    private Integer number;
    private String name;
    private String domainName;
    private String address;
    private String status;
    private String dist;

}
