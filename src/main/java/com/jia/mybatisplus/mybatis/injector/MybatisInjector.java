package com.jia.mybatisplus.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector.LogicDeleteBatchInjector;
import com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector.LogicDeleteByIdInjector;
import com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector.LogicDeleteBatchByIdsInjector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author:huangyongjia
 * @Date:2021/4/19 19:35
 * @Description: 扩展mapper方法
 */
//先注释掉注入，加上此注解就会把扩展方法注入到sql注入器中
@Component
public class MybatisInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //替换掉原来的三个删除方法
        return Stream.of(
                new Insert(),
                new LogicDeleteBatchInjector(),
                new DeleteByMap(),
                new LogicDeleteByIdInjector(),
                new LogicDeleteBatchByIdsInjector(),
                new Update(),
                new UpdateById(),
                new SelectById(),
                new SelectBatchByIds(),
                new SelectByMap(),
                new SelectOne(),
                new SelectCount(),
                new SelectMaps(),
                new SelectMapsPage(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage()
        ).collect(toList());
    }
}
