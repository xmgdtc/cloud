package org.xmgdtc.account.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmgdtc.account.controller.ISystemController;
import org.xmgdtc.account.services.ISystemSVC;
import org.xmgdtc.api.request.RestfulRequest;
import org.xmgdtc.api.request.payload.IdPayload;
import org.xmgdtc.api.request.payload.ItemPayload;
import org.xmgdtc.api.response.ItemResponse;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.view.system.SystemView;
import org.xmgdtc.common.controller.BaseController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/system")
public class SystemControllerImpl extends BaseController implements ISystemController {

    @Autowired
    private ISystemSVC systemSVC;

    @RequestMapping(value = "/createSystem", method = {RequestMethod.POST})
    @Override
    public RestfulResponse<ItemResponse<SystemView>> createSystem(@RequestBody @NotNull @Valid RestfulRequest<ItemPayload<SystemView>> request) {
        SystemView view = systemSVC.createSystem(request.getPayload().getItem());
        return new RestfulResponse<>(new ItemResponse<>(view));
    }

    @RequestMapping(value = "/editSystem", method = {RequestMethod.POST})
    @Override
    public RestfulResponse<ItemResponse<SystemView>> editSystem(@RequestBody @NotNull @Valid RestfulRequest<ItemPayload<SystemView>> request) {
        SystemView view = systemSVC.editSystem(request.getPayload().getItem());
        return new RestfulResponse<>(new ItemResponse<>(view));
    }

    @RequestMapping(value = "/deleteSystem", method = {RequestMethod.POST})
    @Override
    public RestfulResponse<ItemResponse<SystemView>> deleteSystem(@RequestBody @NotNull @Valid RestfulRequest<IdPayload> request) {
        SystemView view = systemSVC.deleteSystem(request.getPayload().getId());
        return new RestfulResponse<>(new ItemResponse<>(view));
    }

    @RequestMapping(value = "/findSystem", method = {RequestMethod.POST})
    @Override
    public RestfulResponse<ItemResponse<SystemView>> findSystem(@RequestBody @NotNull @Valid RestfulRequest<IdPayload> request) {
        SystemView view = systemSVC.findSystem(request.getPayload().getId());
        return new RestfulResponse<>(new ItemResponse<>(view));
    }
}
