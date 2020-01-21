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
public class OracleBaseEntity {

    private Long id;
    @TableLogic
    private String deleted;
    @Version
    private Long version;

}
