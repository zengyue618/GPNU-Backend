package com.gpnu.repository.task;

import com.gpnu.entity.task.JobExecData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobExecDataRepository extends JpaRepository<JobExecData, Long> {
    List<JobExecData> findByTaskIdAndExecId(String taskId, String execId);
    List<JobExecData> findByExecId(String execId);
    JobExecData findByExecIdAndJobName(String execId, String jobName);
}
