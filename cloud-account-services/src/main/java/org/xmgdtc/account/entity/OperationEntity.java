package org.xmgdtc.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_operation")
public class OperationEntity extends BaseEntity {

    @Column(name = "ops_path")
    private String path;

    @Column(name = "ops_desc")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_id", insertable = false, updatable = false)
    private ResourceEntity resource;

}