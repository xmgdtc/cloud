package org.xmgdtc.account.dao;

import org.xmgdtc.account.entity.SystemEntity;
import org.xmgdtc.api.params.BaseParams;

public interface ISystemDAO {

    /**
     * 新建系统
     *
     * @param entity
     * @return
     */
    SystemEntity save(SystemEntity entity);


    /**
     * 删除系统
     *
     * @param id
     * @return
     */
    SystemEntity delete(String id);

    /**
     * 单个查找系统
     *
     * @param id
     * @return
     */
    SystemEntity findById(String id);


    /**
     * 查找列表
     *
     * @param params
     * @return
     */
    SystemEntity list(BaseParams params);

}
