package org.xmgdtc.account.entity.test;

import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "t_paytype_a")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class PayTypeAEntity extends AbsPayTypeEntity {

    @Column(name = "a")
    private String a;
}
