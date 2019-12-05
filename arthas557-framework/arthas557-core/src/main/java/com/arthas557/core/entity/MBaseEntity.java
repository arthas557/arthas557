package com.arthas557.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Mysql基类
 */
@Getter
@Setter
public class MBaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Date createTime;
    private Date modifyTime;
    @TableLogic
    private String deleted;
    @Version
    private Long version;


}
