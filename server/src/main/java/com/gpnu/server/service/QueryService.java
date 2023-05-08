package com.gpnu.server.service;

import com.gpnu.entity.query.SavedSql;
import com.gpnu.server.query.QueryObject;
import com.gpnu.server.query.dataframe.DataFrame;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface QueryService {
    //执行查询 返回值类型 为 map 和 DataFrame
    Map<String, Object> executeQuery(QueryObject queryObject, int pageIndex) throws ExecutionException, InterruptedException, Exception;

    DataFrame executeDataFrame(QueryObject queryObject, int pageIndex) throws ExecutionException, InterruptedException;

    //获取schemas
    List<String> getSchemas(QueryObject queryObject) throws Exception;

    //获取tables
    List<String> getTables(QueryObject queryObject) throws ExecutionException, SQLException;

    //获取表详情
    Map<String, String> getTableInfo(QueryObject queryObject) throws ExecutionException, SQLException;
    //常用sql的保存和查询

    void saveQuerySql(SavedSql savedSql);

    List<SavedSql> getQuerySqlByCreator(String creator);
}
