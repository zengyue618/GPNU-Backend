package com.gpnu.repository.task;

import com.gpnu.entity.task.TaskWorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskWorkFlowRepository extends JpaRepository<TaskWorkFlow, String> {

  TaskWorkFlow findOneByName(String name);
}
