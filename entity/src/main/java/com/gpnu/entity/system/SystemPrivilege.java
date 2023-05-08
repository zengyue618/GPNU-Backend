package com.gpnu.entity.system;

import com.gpnu.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "gpnu_system_privilege")
public class SystemPrivilege extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String team;
  @Enumerated(EnumType.STRING)
  private PrivilegeType privilegeType;

  @Override
  public String toString(){
    return String.format("SystemPrivilege Type %s Team %s", privilegeType,team);
  }
}
