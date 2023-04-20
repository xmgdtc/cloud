package org.xmgdtc.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_role")
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    @Column(name = "system_id")
    private String systemId;


    @Column(name = "name")
    private String name;

}
