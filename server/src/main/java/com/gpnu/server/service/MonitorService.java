package com.gpnu.server.service;

import com.gpnu.entity.cluster.HdfsSummary;
import com.gpnu.entity.cluster.QueueMetrics;
import com.gpnu.entity.cluster.YarnSummary;

import java.util.List;

public interface MonitorService {
    //添加hdfs summary
    void addHdfsSummary(HdfsSummary hdfsSummary);

    //添加yarn summary
    void addYarnSummary(YarnSummary yarnSummary);

    //添加queue metric
    void addQueueMetrics(List<QueueMetrics> queueMetrics);

    //根据时间查找最近一次的hdfs summary
    HdfsSummary findHdfsSummary(long selectTime);

    //根据时间查找最近一次的yarn summary
    YarnSummary findYarnSummary(long selectTime);

    //根据时间查找最近一次的queue metric
    List<QueueMetrics> findQueueMetrics(long selectTime);

    //查询某段时间hdfs summary
    List<HdfsSummary> findHdfsSummaryBetween(long startTime, long endTime);

    //查询某段时间yarn summary
    List<YarnSummary> findYarnSummaryBetween(long startTime, long endTime);

}
