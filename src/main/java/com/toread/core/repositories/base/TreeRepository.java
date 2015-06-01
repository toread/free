/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.repositories.base;

import com.toread.core.domain.base.TreeEntity_;
import com.toread.core.domain.base.tree.TreeEntity;
import org.springframework.util.Assert;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author lizhibing
 * @ClassName: TreeRepositoryImpl
 * @Description:
 * @date 2015-04-29 16:35
 */
public class TreeRepository<T extends TreeEntity> extends BaseRepository<T>{

    public List<T> findChildes(T t) {
        Assert.hasText(t.getParentUuid(), "父节点不能为空");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
        Root<T> tRoot = cq.from(getDomainClass());
        Predicate parentUuidPredicate  = cb.equal(tRoot.get(TreeEntity_.parentUuid.getName()), t.getParentUuid());
        cq.where(parentUuidPredicate);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public T findFather(T t) {
        Assert.hasText(t.getParentUuid(),"父节点不能为空");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
        Root<T> tRoot = cq.from(getDomainClass());
        Predicate parentUuidPredicate  = cb.equal(tRoot.get(TreeEntity_.uuid),t.getParentUuid());
        cq.where(parentUuidPredicate);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }

    public void addChild(T father,T child){
        Assert.hasText(father.getUuid(),"父节点不能为空");
        child.setParentUuid(father.getUuid());
        save(child);
    }

    public Integer deleteChild(T child){
        Integer total  = 0;
        List<T> childes = findChildes(child);
        for (T t : childes) {
            total++;
            delete(t);
            deleteChild(child);
        }
        return  total;
    }
}
