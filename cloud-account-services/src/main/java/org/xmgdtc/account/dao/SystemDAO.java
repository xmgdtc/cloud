package org.xmgdtc.account.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmgdtc.account.entity.SystemEntity;
import org.xmgdtc.account.repo.SystemRepo;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;

@Repository
public class SystemDAO {

    @Autowired
    public SystemRepo repo;

    public SystemEntity save(SystemEntity entity) {
        return repo.saveAndFlush(entity);
    }


    public SystemEntity delete(String id) {
        SystemEntity system = findById(id);
        system.setDeleted(true);
        return save(system);
    }

    public SystemEntity findById(String id) {
        return repo.findById(id).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_ITEM_NOT_EXISTS));
    }
}
