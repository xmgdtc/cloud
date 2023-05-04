package org.xmgdtc.account.services;

import org.xmgdtc.api.params.system.SystemParams;
import org.xmgdtc.api.view.system.SystemView;

import java.util.List;

/**
 * 系统（微服务） 资源（controller整体） 操作（controller整体每个个体） 的增删改查
 */
public interface ISystem {

    /**
     * 创建系统
     *
     * @param systemView
     * @return
     */
    public SystemView createSystem(SystemView systemView);

    /**
     * 编辑系统
     *
     * @param systemView
     * @return
     */
    public SystemView editSystem(SystemView systemView);

    /**
     * 删除系统
     *
     * @param id
     * @return
     */
    public SystemView deleteSystem(String id);

    /**
     * 列表查询系统
     *
     * @param params
     * @return
     */
    public List<SystemView> ListSystem(SystemParams params);

}
