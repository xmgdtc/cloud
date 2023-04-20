package org.xmgdtc.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.xmgdtc.common.mapper.CommonMapper;


public class BaseSVC {

    @Autowired
    protected ApplicationContext context;

    @Autowired
    private CommonMapper commonMapper;

    public CommonMapper buildCommonMapper() {
        return this.commonMapper;
    }

}
