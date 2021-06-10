package com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.jia.mybatisplus.mybatis.utils.MybatisFillUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author:huangyongjia
 * @Date:2021/4/20 9:58
 * @Description:根据id逻辑删除自动填充字段
 */
public class LogicDeleteByIdInjector extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BY_ID;
        if (tableInfo.isLogicDelete()) {
            String sqlSet = MybatisFillUtils.logicDelSetSql(tableInfo);
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
                    tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                    tableInfo.getLogicDeleteSql(true, true));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return addUpdateMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource);
        } else {
            sqlMethod = SqlMethod.DELETE_BY_ID;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                    tableInfo.getKeyProperty());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return addDeleteMappedStatement(mapperClass, getMethod(sqlMethod), sqlSource);
        }

    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        // 可自定义 mapper 方法名
        return "deleteById";
    }
}
