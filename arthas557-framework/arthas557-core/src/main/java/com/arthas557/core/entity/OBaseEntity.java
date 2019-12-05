package com.arthas557.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Oracle基类
 * @see KeySequence
 */
@Getter
@Setter
public class OBaseEntity {

    private Long id;
    private Date createTime;
    private Date modifyTime;
    @TableLogic
    private String deleted;
    @Version
    private Long version;

}
