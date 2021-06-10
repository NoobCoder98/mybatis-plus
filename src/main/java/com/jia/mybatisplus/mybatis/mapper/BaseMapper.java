package com.jia.mybatisplus.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huangyongjia
 * @Date:2021/4/20 8:51
 * @Description:
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T>   {
    int logicDeleteById(T entity);
    int logicDeleteBatch(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
}
