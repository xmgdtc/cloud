package org.xmgdtc.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "t_resource")
public class ResourceEntity extends BaseEntity {

    @Column(name = "res_path")
    private String path;

    @Column(name = "res_name")
    private String name;

    @Column(name = "system_id")
    private String systemId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "res_id", nullable = false)
    private Set<OperationEntity> operations;

}
