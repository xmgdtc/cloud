package org.xmgdtc.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "t_account_auth")
@Getter
@Setter
public class AccountAuthEntity extends BaseEntity {


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private AccountEntity account;

    @Column(name = "account_id", insertable = true, updatable = true)
    private String accountId;

    @Column(name = "password")
    private String password;


}
