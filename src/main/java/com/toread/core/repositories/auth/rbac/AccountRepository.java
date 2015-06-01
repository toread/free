/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.repositories.auth.rbac;

import com.toread.core.domain.auth.rbac.Account;
import com.toread.core.domain.auth.rbac.Account_;
import com.toread.core.repositories.base.BaseRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lizhibing
 * @ClassName: RoleReposotory
 * @Description:
 * @date 2015-04-29 17:22
 */
@Repository
public class AccountRepository extends BaseRepository<Account> {
    protected  static final Logger LOGGER = Logger.getLogger(AccountRepository.class);
    /**
     * 通过code及密码获取账户信息
     * @param account
     * @return
     */
    public Account findByCodeAndPwd(Account account){
        Account fdm = null;
        Assert.hasText(account.getCode(),"账户代码不能为空");
        Assert.hasText(account.getPwd(),"账户密码不能为空");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account>  cq = cb.createQuery(getDomainClass());
        Root<Account> accountRoot =cq.from(Account.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(accountRoot.get(Account_.code),account.getCode()));
        predicates.add(cb.equal(accountRoot.get(Account_.pwd), account.getPwd()));
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        try{
            fdm= em.createQuery(cq).getSingleResult();
        }catch (NonUniqueResultException e){
            LOGGER.error(e);
        }
        return fdm;
    }
}
