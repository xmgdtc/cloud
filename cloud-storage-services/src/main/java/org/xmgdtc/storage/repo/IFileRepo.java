package org.xmgdtc.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.xmgdtc.storage.entity.FileEntity;

public interface IFileRepo extends JpaRepository<FileEntity, String>, JpaSpecificationExecutor<FileEntity> {

}
