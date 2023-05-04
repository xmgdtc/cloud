package org.xmgdtc.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "t_system")
public class SystemEntity extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "system_id", insertable = false, updatable = false)
    private Set<RoleEntity> roles;

}
