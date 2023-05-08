package com.gpnu.repository.meta;

import com.gpnu.entity.meta.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo,Long> {
    ProjectInfo findByName(String name);
}
