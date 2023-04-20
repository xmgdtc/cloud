package org.xmgdtc.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.account.entity.SystemEntity;

public interface SystemRepo extends JpaRepository<SystemEntity, String>, JpaSpecificationExecutor<SystemEntity> {


}
