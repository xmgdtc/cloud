package org.xmgdtc.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmgdtc.account.services.ResourceSVC;
import org.xmgdtc.api.request.ItemRequest;
import org.xmgdtc.api.request.RestfulRequest;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.view.resource.ResourceView;
import org.xmgdtc.common.controller.BaseController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceSVC resourceSVC;

    @PostMapping("/create")
    public RestfulResponse createResource(@RequestBody @NotNull @NotNull RestfulRequest<ItemRequest<ResourceView>> request) {
        resourceSVC.save(request.getPayload().getItem());
        return new RestfulResponse();

    }

}
