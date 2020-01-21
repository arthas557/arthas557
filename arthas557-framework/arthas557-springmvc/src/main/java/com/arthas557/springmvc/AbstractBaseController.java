package com.arthas557.springmvc;

import com.arthas557.core.entity.MysqlBaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public class AbstractBaseController<T extends MysqlBaseEntity> {

    private BaseMapper<T> mapper;


}
