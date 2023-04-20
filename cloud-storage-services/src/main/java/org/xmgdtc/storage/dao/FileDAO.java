package org.xmgdtc.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.common.dao.BaseDAO;
import org.xmgdtc.storage.entity.FileEntity;
import org.xmgdtc.storage.repo.IFileRepo;

@Repository
public class FileDAO extends BaseDAO {

    @Autowired
    public IFileRepo repo;

    public FileEntity save(FileEntity file) {
        file.init();
        return repo.saveAndFlush(file);
    }

    public FileEntity findById(String id) {
        return repo.findById(id).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_ITEM_NOT_EXISTS));
    }

}
