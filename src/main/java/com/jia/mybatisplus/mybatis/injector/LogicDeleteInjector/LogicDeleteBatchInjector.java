package com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.jia.mybatisplus.mybatis.utils.MybatisFillUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author:huangyongjia
 * @Date:2021/4/22 17:18
 * @Description:根据条件批量逻辑删除
 */
public class LogicDeleteBatchInjector extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE;
        if (tableInfo.isLogicDelete()) {
            String sqlSet = MybatisFillUtils.logicDelSetSql(tableInfo);

            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
                        sqlWhereEntityWrapper(true, tableInfo), sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return this.addUpdateMappedStatement(mapperClass, modelClass, this.getMethod(sqlMethod), sqlSource);
        } else {
            sqlMethod = SqlMethod.DELETE;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                    sqlWhereEntityWrapper(true, tableInfo),
                    sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return this.addDeleteMappedStatement(mapperClass, this.getMethod(sqlMethod), sqlSource);
        }
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        // 可自定义 mapper 方法名
        return "delete";
    }
}
