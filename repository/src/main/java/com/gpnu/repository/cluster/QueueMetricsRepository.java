package com.gpnu.repository.cluster;

import com.gpnu.entity.cluster.QueueMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueMetricsRepository extends JpaRepository<QueueMetrics, Long> {
    List<QueueMetrics> findByCreateTime(Long selectTime);
}
