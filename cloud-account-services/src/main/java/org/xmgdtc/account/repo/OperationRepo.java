package org.xmgdtc.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.account.entity.OperationEntity;

public interface OperationRepo extends JpaRepository<OperationEntity, String>, JpaSpecificationExecutor<OperationEntity> {


}
