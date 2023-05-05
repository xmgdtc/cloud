package org.xmgdtc.account.controller;

import org.xmgdtc.api.request.RestfulRequest;
import org.xmgdtc.api.request.payload.IdPayload;
import org.xmgdtc.api.request.payload.ItemPayload;
import org.xmgdtc.api.response.ItemResponse;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.view.system.SystemView;

public interface ISystemController {

    /**
     * 创建系统
     *
     * @param request
     * @return
     */
    RestfulResponse<ItemResponse<SystemView>> createSystem(RestfulRequest<ItemPayload<SystemView>> request);


    /**
     * 编辑系统
     *
     * @param request
     * @return
     */
    RestfulResponse<ItemResponse<SystemView>> editSystem(RestfulRequest<ItemPayload<SystemView>> request);


    /**
     * 删除系统
     *
     * @param request
     * @return
     */
    RestfulResponse<ItemResponse<SystemView>> deleteSystem(RestfulRequest<IdPayload> request);


    /**
     * 查找系统
     *
     * @param request
     * @return
     */
    RestfulResponse<ItemResponse<SystemView>> findSystem(RestfulRequest<IdPayload> request);

}
