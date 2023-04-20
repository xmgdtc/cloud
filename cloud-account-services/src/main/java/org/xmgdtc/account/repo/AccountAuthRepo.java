package org.xmgdtc.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.account.entity.AccountAuthEntity;
import org.xmgdtc.account.entity.AccountEntity;

public interface AccountAuthRepo extends JpaRepository<AccountAuthEntity, String>, JpaSpecificationExecutor<AccountAuthEntity> {

}
