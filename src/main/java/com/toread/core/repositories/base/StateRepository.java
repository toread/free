/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.repositories.base;

import com.toread.core.domain.base.StateEntity;
import com.toread.core.domain.base.StateEntity_;
import com.toread.core.eume.EnumState;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author lizhibing
 * @ClassName: StateService
 * @Description:
 * @date 2015-04-29 21:49
 */
public class StateRepository<T extends StateEntity> extends BaseRepository<T>{

    protected Predicate statePredicate(EnumState enumState){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq =  cb.createQuery(getDomainClass());
        Root<T> pet = cq.from(getDomainClass());
        return cb.equal(pet.get(StateEntity_.state), enumState);
    }
}
