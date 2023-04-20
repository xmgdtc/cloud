package org.xmgdtc.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.account.entity.ResourceEntity;

public interface ResourceRepo extends JpaRepository<ResourceEntity, String>, JpaSpecificationExecutor<ResourceEntity> {


}
