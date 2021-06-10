package com.jia.mybatisplus.mybatis.utils;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jia.mybatisplus.mybatis.annotation.LogicDelFill;

import java.lang.reflect.Field;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @Author:huangyongjia
 * @Date:2021/6/9 9:13
 * @Description:
 */
public class MybatisFillUtils {
    private static boolean isLogicDelFill(Field field) {
        return field.getAnnotation(LogicDelFill.class) != null;
    }

    private static String getFillSql(TableFieldInfo info) {
        LogicDelFill logicDelFill = info.getField().getAnnotation(LogicDelFill.class);
        StringBuilder sb = new StringBuilder(info.getColumn()).append("=").append(logicDelFill.value()).append(",");
        return sb.toString();
    }

    private static List<TableFieldInfo> getFillFieldInfoList(TableInfo tableInfo) {
        return tableInfo.getFieldList().stream()
                .filter( i -> MybatisFillUtils.isLogicDelFill(i.getField()))
                .collect(toList());
    }

    public static String logicDelSetSql(TableInfo tableInfo) {
        List<TableFieldInfo> list = getFillFieldInfoList(tableInfo);
        String sqlSet = null;
        if (!CollectionUtils.isEmpty(list)) {
            sqlSet = list.stream().map(i -> MybatisFillUtils.getFillSql(i)).collect(joining(""));
        }
        sqlSet += tableInfo.getLogicDeleteSql(false, false);

        return "SET " + sqlSet;
    }
}
