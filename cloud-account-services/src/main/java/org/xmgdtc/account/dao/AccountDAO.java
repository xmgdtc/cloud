package org.xmgdtc.account.dao;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.xmgdtc.account.entity.AccountEntity;
import org.xmgdtc.account.repo.AccountRepo;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.common.dao.BaseDAO;
import org.xmgdtc.common.dao.PredicatesHelper;

import java.util.List;
import java.util.concurrent.Executors;

@Repository
public class AccountDAO extends BaseDAO {

    @Autowired
    public AccountRepo repo;

    public AccountEntity save(AccountEntity account) {
        account.init();
        return repo.saveAndFlush(account);
    }

    public AccountEntity update(AccountEntity account) {
        return repo.saveAndFlush(account);
    }

    public AccountEntity findById(String id) {
        return repo.findById(id).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_ITEM_NOT_EXISTS));
    }

    public AccountEntity findByUserId(String uid) {
        return repo.findOne((root, query, cb) -> {
            return query.where(cb.equal(root.get("uid"), uid)).getRestriction();
        }).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_USER_NOT_EXISTS));
    }

    public List<AccountEntity> findAll() {
        return repo.findAll();
    }


    @SneakyThrows
    // @PostConstruct
    public void test() {
        var e = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {


            e.submit(() -> {
                Specification specification = (root, query, cb) -> {
                    PredicatesHelper local = buildPredicateHelper();
                    System.out.println(local);
                    local.addPredicate(cb.equal(root.get("uid"), "acb"));
                    System.out.println(local);
                    return query.where(local.getPredicateArray()).getRestriction();
                };
                repo.findOne(specification);
            });
        }

    }
}
