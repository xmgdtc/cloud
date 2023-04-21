package org.xmgdtc.account.entity.test;

import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbsPayTypeEntity extends BaseEntity {

    @Column(name = "test")
    private String test;
}
