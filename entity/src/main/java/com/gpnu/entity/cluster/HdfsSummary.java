package com.gpnu.entity.cluster;

import com.gpnu.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "hdfs_summary")
public class HdfsSummary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long total;
    private Long dfsUsed;
    private Float percentUsed;
    private Long dfsFree;
    private Long nonDfsUsed;
    private Long totalBlocks;
    private Long totalFiles;
    private Long missingBlocks;
    private Integer liveDataNodeNums;
    private Integer deadDataNodeNums;
    private Integer volumeFailuresTotal;
}
