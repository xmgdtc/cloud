package org.xmgdtc.common.dao;

import org.springframework.beans.factory.annotation.Lookup;

public interface IBaseDAO {

    @Lookup
    PredicatesHelper buildPredicateHelper();
}
