package com.jia.mybatisplus.mybatis.injector.LogicDeleteInjector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.jia.mybatisplus.mybatis.utils.MybatisFillUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author:huangyongjia
 * @Date:2021/6/9 9:10
 * @Description:
 */
public class LogicDeleteBatchByIdsInjector extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BATCH_BY_IDS;
        if (tableInfo.isLogicDelete()) {
            String sqlSet = MybatisFillUtils.logicDelSetSql(tableInfo);
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
                    tableInfo.getKeyColumn(),
                    SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA),
                    tableInfo.getLogicDeleteSql(true, true));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
            return addUpdateMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource);
        } else {
            sqlMethod = SqlMethod.DELETE_BATCH_BY_IDS;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                    SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
            return this.addDeleteMappedStatement(mapperClass, getMethod(sqlMethod), sqlSource);
        }
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return "deleteBatchIds";
    }
}
