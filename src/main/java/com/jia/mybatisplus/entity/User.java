package com.jia.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jia.mybatisplus.mybatis.annotation.LogicDelFill;
import lombok.Data;

/**
 * @Author:huangyongjia
 * @Date:2021/6/10 17:22
 * @Description:
 */
@Data
public class User {
    private long id;
    //自动填充注解
    @LogicDelFill("'已删除'")
    private String name;
    private int age;
    //逻辑删除注解
    @TableLogic
    private String delflag;
    //自动填充注解
    @LogicDelFill("now()")
    private String deltime;
}
