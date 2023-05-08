package com.gpnu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {
    @JsonIgnore
    private boolean isTrash = false;
    private Long createTime;
}
