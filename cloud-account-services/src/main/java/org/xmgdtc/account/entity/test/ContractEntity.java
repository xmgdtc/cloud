package org.xmgdtc.account.entity.test;

import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "t_contract")
public class ContractEntity extends BaseEntity {

    @Column(name = "test")
    private String test;

    @OneToOne
    @JoinColumn(name = "paytype_id")
    private AbsPayTypeEntity payType;

}
