package org.xmgdtc.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmgdtc.account.dao.ISystemDAO;
import org.xmgdtc.account.entity.SystemEntity;
import org.xmgdtc.account.repo.SystemRepo;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.api.params.BaseParams;
import org.xmgdtc.common.dao.BaseDAO;

@Repository
public class SystemDAOImpl extends BaseDAO implements ISystemDAO {

    @Autowired
    private SystemRepo repo;

    @Override
    public SystemEntity save(SystemEntity entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public SystemEntity delete(String id) {
        SystemEntity system = findById(id);
        system.setDeleted(true);
        return save(system);
    }

    @Override
    public SystemEntity findById(String id) {
        return repo.findById(id).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_ITEM_NOT_EXISTS));
    }

    @Override
    public SystemEntity list(BaseParams params) {
        return null;
    }
}
