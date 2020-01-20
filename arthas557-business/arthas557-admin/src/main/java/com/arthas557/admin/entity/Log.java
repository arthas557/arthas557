package com.arthas557.admin.entity;

import com.arthas557.core.entity.MysqlBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Log")
public class Log extends MysqlBaseEntity {

    private String content;
}
