package org.xmgdtc.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.xmgdtc.account.dao.AccountAuthDAO;
import org.xmgdtc.account.dao.AccountDAO;
import org.xmgdtc.account.entity.AccountAuthEntity;
import org.xmgdtc.account.entity.AccountEntity;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.api.view.account.AccountView;
import org.xmgdtc.api.view.account.SignUpView;
import org.xmgdtc.common.mapper.CommonMapper;
import org.xmgdtc.common.service.BaseSVC;
import org.xmgdtc.common.utils.BeanUtil;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountSVC extends BaseSVC {


    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountAuthDAO accountAuthDAO;


    /**
     * 注册
     *
     * @param signUpView
     * @return
     */
    @Transactional
    public AccountView signUp(SignUpView signUpView) {

        // 个人基本信息
        CommonMapper commonMapper = buildCommonMapper();
        AccountEntity accountEntity = commonMapper.map(signUpView, AccountEntity.class);
        accountDAO.save(accountEntity);


        //认证信息
        AccountAuthEntity accountAuthEntity = new AccountAuthEntity();
        accountAuthEntity.setPassword(signUpView.getPassword());
        accountAuthEntity.setAccountId(accountEntity.getId());
        accountAuthDAO.save(accountAuthEntity);

        return commonMapper.map(accountEntity, AccountView.class);
    }


    @Transactional
    public AccountView save(AccountView account) {

        CommonMapper commonMapper = buildCommonMapper();
        AccountEntity accountEntity = commonMapper.map(account, AccountEntity.class);
        accountEntity = accountDAO.save(accountEntity);
        return commonMapper.map(accountEntity, AccountView.class);
    }

    @Transactional
    public AccountView update(AccountView account) {

        Assert.notNull(account.getId(), "id不能为空");
        CommonMapper commonMapper = buildCommonMapper();
        AccountEntity accountEntity = commonMapper.map(account, AccountEntity.class);

        AccountEntity old = accountDAO.findById(account.getId());
        BeanUtil.mergeObject(accountEntity, old);

        accountEntity = accountDAO.update(accountEntity);
        return commonMapper.map(accountEntity, AccountView.class);

    }

    public List<AccountView> findAll() {
        CommonMapper commonMapper = buildCommonMapper();
        List<AccountEntity> entities = accountDAO.findAll();
        return commonMapper.mapList(entities, AccountView.class);

    }

    public AccountView findByUserId(String uid) {
        CommonMapper commonMapper = buildCommonMapper();
        AccountEntity entity = accountDAO.findByUserId(uid);
        return commonMapper.map(entity, AccountView.class);
    }

    public AccountView verifyAndGet(String uid, String password) {
        CommonMapper commonMapper = buildCommonMapper();
        AccountEntity entity = accountDAO.findByUserId(uid);
        if (ObjectUtils.isEmpty(password) || !password.equals(entity.getAuth().getPassword())) {
            throw new CloudBizException(CloudExceptionEnum.ERR_PASSWORD_NOT_CORRECT);
        }
        return commonMapper.map(entity, AccountView.class);
    }

}
