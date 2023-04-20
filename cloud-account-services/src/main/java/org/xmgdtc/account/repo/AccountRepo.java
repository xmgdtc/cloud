package org.xmgdtc.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.account.entity.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, String>, JpaSpecificationExecutor<AccountEntity> {

}
