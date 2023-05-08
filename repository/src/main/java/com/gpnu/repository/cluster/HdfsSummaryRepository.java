package com.gpnu.repository.cluster;

import com.gpnu.entity.cluster.HdfsSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HdfsSummaryRepository extends JpaRepository<HdfsSummary, Long> {
    HdfsSummary findTop1ByIsTrashFalseAndCreateTimeLessThanEqualOrderByCreateTimeDesc(Long selectTime);

    List<HdfsSummary> findByIsTrashFalseAndCreateTimeBetweenOrderByCreateTimeAsc(Long startTime, Long endTime);

}
