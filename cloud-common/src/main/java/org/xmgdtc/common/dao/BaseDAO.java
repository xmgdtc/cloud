package org.xmgdtc.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;


public class BaseDAO {
    @Autowired
    private PredicatesHelper predicatesHelper;

    /**
     * 获取一个新的实例
     *
     * @return
     */
    @Lookup
    public PredicatesHelper buildPredicateHelper() {
        return null;
    }

    ;

}
