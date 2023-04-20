package org.xmgdtc.account.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmgdtc.account.entity.AccountAuthEntity;
import org.xmgdtc.account.repo.AccountAuthRepo;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.common.dao.BaseDAO;

import java.util.List;

@Repository
public class AccountAuthDAO extends BaseDAO {

    @Autowired
    public AccountAuthRepo repo;

    public AccountAuthEntity save(AccountAuthEntity entity) {
        entity.init();
        return repo.saveAndFlush(entity);
    }

    public AccountAuthEntity update(AccountAuthEntity entity) {
        return repo.saveAndFlush(entity);
    }

    public AccountAuthEntity findById(String id) {
        return repo.findById(id).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_ITEM_NOT_EXISTS));
    }

    public List<AccountAuthEntity> findAll() {
        return repo.findAll();
    }


}
