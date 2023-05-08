package com.gpnu.server.query;

import com.gpnu.core.exception.ErrorCodes;
import com.gpnu.core.exception.GPNUException;
import com.gpnu.server.query.dataframe.DataFrame;

import java.sql.*;

public class JdbcDao {
    //查询 返回 dataframe
    public static DataFrame queryAsDataFrame(QueryObject jdbcQo, Connection conn) {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (conn.getMetaData().getDatabaseProductName().toLowerCase().contains("presto")) {
                statement = conn.createStatement();
                resultSet = statement.executeQuery(jdbcQo.getSql());
            } else {
                preparedStatement = conn.prepareStatement(jdbcQo.getSql(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = preparedStatement.executeQuery();
            }
            //将resultset 转换为dataframe
            return getResultWrapper().setResources(jdbcQo, statement, preparedStatement, conn).wrapData(resultSet);
        } catch (SQLException e) {
            throw new GPNUException("query error" + e.getMessage(), ErrorCodes.SYSTEM_EXCEPTION);
        }
    }


    //查询返回resultset
    public static ResultSet query(QueryObject jdbcQo, Connection conn) {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (conn.getMetaData().getDatabaseProductName().toLowerCase().contains("presto")) {
                statement = conn.createStatement();
                resultSet = statement.executeQuery(jdbcQo.getSql());
            } else {
                preparedStatement = conn.prepareStatement(jdbcQo.getSql(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = preparedStatement.executeQuery();
            }
            return resultSet;
        } catch (SQLException e) {
            throw new GPNUException("query error" + e.getMessage(), ErrorCodes.SYSTEM_EXCEPTION);
        }
    }

    private static JdbcResultSetDataFrameWrapper getResultWrapper() {
        return new JdbcResultSetDataFrameWrapper();
    }
}
