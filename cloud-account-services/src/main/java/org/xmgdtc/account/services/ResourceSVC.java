package org.xmgdtc.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xmgdtc.account.dao.ResourceDAO;
import org.xmgdtc.account.entity.ResourceEntity;
import org.xmgdtc.api.view.system.ResourceView;
import org.xmgdtc.common.entity.BaseEntity;
import org.xmgdtc.common.mapper.CommonMapper;
import org.xmgdtc.common.service.BaseSVC;

@Service
@Transactional(readOnly = true)
public class ResourceSVC extends BaseSVC {


    @Autowired
    private ResourceDAO resourceDAO;


    @Transactional
    public void save(ResourceView resourceView) {
        CommonMapper commonMapper = buildCommonMapper();

        ResourceEntity entity = commonMapper.map(resourceView, ResourceEntity.class);
        entity.init();
        if (!CollectionUtils.isEmpty(entity.getOperations())) {
            entity.getOperations().forEach(BaseEntity::init);
        }
        resourceDAO.save(entity);
    }


}
