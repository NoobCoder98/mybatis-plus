package com.jia.mybatisplus.mybatis.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogicDelFill {
    //用于调用逻辑删除方法的自动填充，为了兼容mysql函数，如果是字符串，需要自行在两边加上单引号
    String value() default "null";
}
