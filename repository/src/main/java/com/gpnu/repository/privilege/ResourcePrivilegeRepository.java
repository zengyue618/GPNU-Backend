package com.gpnu.repository.privilege;

import com.gpnu.entity.privilege.ResourcePrivilege;
import com.gpnu.entity.privilege.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcePrivilegeRepository extends JpaRepository<ResourcePrivilege, Long> {
  ResourcePrivilege findOneByTeamAndResourceType(String team, ResourceType resourceType);

}
