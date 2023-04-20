package org.xmgdtc.account.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmgdtc.account.entity.ResourceEntity;
import org.xmgdtc.account.repo.ResourceRepo;

@Repository
public class ResourceDAO {

    @Autowired
    public ResourceRepo repo;

    public ResourceEntity save(ResourceEntity entity) {
        return repo.saveAndFlush(entity);
    }


}
