package com.gpnu.repository.system;

import com.gpnu.entity.system.PrivilegeType;
import com.gpnu.entity.system.SystemPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPrivilegeRepository extends JpaRepository<SystemPrivilege, Long> {
  SystemPrivilege findOneByTeamAndPrivilegeType(String team, PrivilegeType privilegeType);

}
