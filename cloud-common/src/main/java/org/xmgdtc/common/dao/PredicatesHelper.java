package org.xmgdtc.common.dao;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PredicatesHelper {

    private List<Predicate> predicates;

    public PredicatesHelper() {
        predicates = new ArrayList<Predicate>();
    }

    public List<Predicate> addPredicate(Predicate predicate, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            this.predicates.add(predicate);
        }

        return this.predicates;
    }

    public List<Predicate> addPredicate(Predicate predicate) {
        this.predicates.add(predicate);
        return this.predicates;
    }

    public Predicate[] getPredicateArray() {
        Predicate[] predicateArray = new Predicate[this.predicates.size()];
        Predicate[] result = this.predicates.toArray(predicateArray);
        return result;
    }


}