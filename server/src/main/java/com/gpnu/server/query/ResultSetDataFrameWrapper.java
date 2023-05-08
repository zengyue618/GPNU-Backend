package com.gpnu.server.query;


import com.gpnu.server.query.dataframe.DataFrame;

public interface ResultSetDataFrameWrapper<R> extends ResultSetWrapper<R, DataFrame> {

  public DataFrame wrapData(R result);
}
