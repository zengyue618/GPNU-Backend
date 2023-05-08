package com.gpnu.repository.var;

import com.gpnu.entity.var.ProjectVar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectVarRepository extends JpaRepository<ProjectVar, Long> {
    ProjectVar findByProjectNameAndName(String projectName, String name);
    List<ProjectVar> findByBindTaskNameAndBindJobName(String taskName, String jobName);
}
