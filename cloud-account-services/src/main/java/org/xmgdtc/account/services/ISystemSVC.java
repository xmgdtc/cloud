package org.xmgdtc.account.services;

import org.xmgdtc.api.params.system.SystemParams;
import org.xmgdtc.api.view.system.SystemView;

import java.util.List;

/**
 * 系统（微服务） 资源（controller整体） 操作（controller整体每个个体） 的增删改查
 */
public interface ISystemSVC {

    /**
     * 创建系统
     *
     * @param systemView
     * @return
     */
    SystemView createSystem(SystemView systemView);

    /**
     * 编辑系统
     *
     * @param systemView
     * @return
     */
    SystemView editSystem(SystemView systemView);

    /**
     * 删除系统
     *
     * @param id
     * @return
     */
    SystemView deleteSystem(String id);

    /**
     * 查找系统
     *
     * @param id
     * @return
     */
    SystemView findSystem(String id);

    /**
     * 列表查询系统
     *
     * @param params
     * @return
     */
    List<SystemView> listSystem(SystemParams params);

}
