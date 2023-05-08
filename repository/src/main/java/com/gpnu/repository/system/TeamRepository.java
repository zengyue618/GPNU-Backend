package com.gpnu.repository.system;

import com.gpnu.entity.system.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
 Team findOneByName(String name);
}
