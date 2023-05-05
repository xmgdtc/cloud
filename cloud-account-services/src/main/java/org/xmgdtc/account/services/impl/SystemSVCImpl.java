package org.xmgdtc.account.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmgdtc.account.dao.ISystemDAO;
import org.xmgdtc.account.entity.SystemEntity;
import org.xmgdtc.account.services.ISystemSVC;
import org.xmgdtc.api.params.system.SystemParams;
import org.xmgdtc.api.view.system.SystemView;
import org.xmgdtc.common.service.BaseSVC;
import org.xmgdtc.common.utils.BeanUtil;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SystemSVCImpl extends BaseSVC implements ISystemSVC {

    @Autowired
    ISystemDAO systemDAO;


    @Transactional
    @Override
    public SystemView createSystem(SystemView systemView) {
        SystemEntity entity = super.buildCommonMapper().map(systemView, SystemEntity.class);
        systemDAO.save(entity);
        return super.buildCommonMapper().map(entity, SystemView.class);
    }

    @Transactional
    @Override
    public SystemView editSystem(SystemView systemView) {
        SystemEntity entity = super.buildCommonMapper().map(systemView, SystemEntity.class);
        SystemEntity old = systemDAO.findById(systemView.getId());
        BeanUtil.mergeObject(entity, old);
        systemDAO.save(entity);
        return super.buildCommonMapper().map(entity, SystemView.class);
    }

    @Transactional
    @Override
    public SystemView deleteSystem(String id) {
        SystemEntity entity = systemDAO.delete(id);
        return super.buildCommonMapper().map(entity, SystemView.class);
    }

    @Override
    public SystemView findSystem(String id) {
        SystemEntity entity = systemDAO.findById(id);
        return super.buildCommonMapper().map(entity, SystemView.class);
    }

    @Override
    public List<SystemView> listSystem(SystemParams params) {
        return null;
    }
}
