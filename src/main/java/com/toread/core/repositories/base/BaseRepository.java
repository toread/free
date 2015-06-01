/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.repositories.base;

import com.toread.common.query.QueryOp;
import com.toread.core.domain.base.PrimaryKeyEntity;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.data.jpa.repository.query.QueryUtils.COUNT_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.DELETE_ALL_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;

/**
 * @author lizhibing
 * @ClassName: BaseRepositoriesImpl
 * @Description:
 * @date 2015-04-29 10:34
 */

public  class BaseRepository<T extends PrimaryKeyEntity> implements CrudRepository<T, String> {
    protected static final Logger LOGGER = Logger.getLogger(BaseRepository.class);
    @PersistenceContext
    protected EntityManager em;
    private JpaEntityInformation<T,?> entityInformation;
    public static final String CND_NOT_MATCHER_MSG ="查询参数%s满足条件正则表达式格式{(\\w+)\\$(\\w+)}无法构建查询条件";
    private static final String ID_MUST_NOT_BE_NULL = "给定的主键不能为空!";
    protected static final Pattern QUERY_PATTERN = Pattern.compile("(\\w+)\\$(\\w+)");

    protected List<Predicate> createPredicates(CriteriaBuilder cb,Root<T>  root,Map<String,Object> query){
        EntityType<T> entityType  =  em.getMetamodel().entity(getDomainClass());
        Set<Attribute<? super T, ?>> types = entityType.getAttributes();
        Assert.notNull(query,"查询map不能为空");
        List<Predicate> predicates = new ArrayList<Predicate>();
        Set<String> queryCnd = query.keySet();
        for (String cnd : queryCnd) {
            Matcher matcher =  QUERY_PATTERN.matcher(cnd);
            //如果指定的条件满足 跳出
            if(!matcher.matches()){
                LOGGER.warn(String.format(CND_NOT_MATCHER_MSG,cnd));
                continue;
            }
            String op = matcher.group(1);
            String property = matcher.group(2);
            Object object = null;
            try {
                Method  method =null;
                Object queryValue = query.get(cnd);
                if(queryValue!=null){
                    method = MethodUtils.getMatchingAccessibleMethod(cb.getClass(), op, root.get(property).getClass(),queryValue.getClass());
                    object = queryMethodInvoke(method,cb,root.get(property),queryValue);
                }else{
                    method = MethodUtils.getMatchingAccessibleMethod(cb.getClass(), op, root.get(property).getClass());
                    object = queryMethodInvoke(method,cb,root.get(property));
                }
            } catch (IllegalAccessException e) {
                throw  new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw  new RuntimeException(e);
            }
            if(object!=null){
                predicates.add((Predicate)object);
            }
        }
        return predicates;
    }

    protected Object queryMethodInvoke(Method method,Object invokeObject,Object... argus) throws InvocationTargetException, IllegalAccessException {
        Object object = null;
        if(method !=null){
            processQueryValue(method,argus);
            object = method.invoke(invokeObject,argus);
        }
        return object;
    }

    protected Object[] processQueryValue(Method method,Object... argus){
        Object[] object = argus;
        if(method.getName().equals(QueryOp.like.getOpMethod())
                ||method.getName().equals(QueryOp.notLike.getOpMethod())){
            if(argus!=null&&argus.length==2){
                object[1]= com.toread.common.util.QueryUtils.processSqlQueryLike((String)argus[1]);
            }
        }
        return object;
    }

    @PostConstruct
    public void postConstruct() {
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(getTClass(),em);
    }

    protected Class<T> getTClass() {
        return (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public<S extends T> S save(S entity){
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        List<S> result = new ArrayList<S>();

        if (entities == null) {
            return result;
        }

        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    public Page<T> query(Pageable pageable,Map<String,Object> parameter){
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<T> result = null;
        Class<T> tClass = getTClass();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T>  root = cq.from(tClass);
        cq = cq.where(cb.and(createPredicates(cb,root,parameter).toArray(new Predicate[predicates.size()])));
        Sort sort = pageable.getSort();
        //排序处理
        if(sort!=null){
            Iterator<Sort.Order> orderIterator =   sort.iterator();
            for (Sort.Order order : sort) {
                if(order.getDirection().equals(Sort.Direction.ASC)){
                    cq.orderBy(cb.asc(root.get(order.getProperty())));
                }else if(order.getDirection().equals(Sort.Direction.DESC)){
                    cq.orderBy(cb.desc(root.get(order.getProperty())));
                }
            }
        }
        result  = em.createQuery(cq).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        Long  total = getRowCount(cq,cb,root);
        List<T> content = total > pageable.getOffset() ? result : Collections.<T> emptyList();
        return new PageImpl<T>(content, pageable, total);
    }


    /**
     * 获取总条数
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param root
     * @return
     */
    protected Long getRowCount(CriteriaQuery criteriaQuery,CriteriaBuilder criteriaBuilder,Root<?> root){
        final CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        final Root<?> entityRoot = countCriteria.from(root.getJavaType());
        entityRoot.alias(root.getAlias());
        doJoins(root.getJoins(), entityRoot);
        Predicate groupRestriction=criteriaQuery.getGroupRestriction();
        Predicate fromRestriction=criteriaQuery.getRestriction();
        if(groupRestriction != null){
            countCriteria.having(groupRestriction);
        }
        if(fromRestriction != null){
            countCriteria.where(fromRestriction);
        }
        countCriteria.groupBy(criteriaQuery.getGroupList());
        countCriteria.distinct(criteriaQuery.isDistinct());
        countCriteria.select(criteriaBuilder.count(entityRoot));
        countCriteria.where(criteriaQuery.getRestriction());
        return em.createQuery(countCriteria).getSingleResult();
    }

    private void doJoins(Set<? extends Join<?, ?>> joins,Root<?> root_){
        for(Join<?,?> join: joins){
            Join<?,?> joined = root_.join(join.getAttribute().getName(),join.getJoinType());
            doJoins(join.getJoins(), joined);
        }
    }

    private void doJoins(Set<? extends Join<?, ?>> joins,Join<?,?> root_){
        for(Join<?,?> join: joins){
            Join<?,?> joined = root_.join(join.getAttribute().getName(),join.getJoinType());
            doJoins(join.getJoins(),joined);
        }
    }

    public long queryPage(Map<String,Object> parameter){
        List<Predicate> predicates = new ArrayList<Predicate>();
        Class<T> tClass = getTClass();
        CriteriaBuilder cbCount = em.getCriteriaBuilder();
        CriteriaQuery<Long> total = cbCount.createQuery(Long.class);
        Root<T>  root = total.from(tClass);
        total.select(cbCount.count(root));
        total.where(cbCount.and(createPredicates(cbCount,root,parameter).toArray(new Predicate[predicates.size()])));
        return em.createQuery(total).getSingleResult();
    }

    @Override
    public T findOne(String uuid) {
        Assert.hasText(uuid, ID_MUST_NOT_BE_NULL);
        Class<T> domainType = getDomainClass();
        return em.find(domainType, uuid);
    }

    @Override
    public boolean exists(String uuid) {
        return  findOne(uuid)!=null;
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> cq  =  em.getCriteriaBuilder().createQuery(getDomainClass());
        cq.select(cq.from(getDomainClass()));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<T> findAll(Iterable<String> uuids) {
        if (uuids == null || !uuids.iterator().hasNext()) {
            return Collections.emptyList();
        }
        List<T> results = new ArrayList<T>();
        for (String id : uuids) {
            results.add(findOne(id));
        }
        return results;
    }

    @Override
    public long count() {
         return em.createQuery(getCountQueryString(), Long.class).getSingleResult();
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        Assert.notNull(uuid, ID_MUST_NOT_BE_NULL);
        T entity = findOne(uuid);
        if (entity == null) {
            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
                    entityInformation.getJavaType(), uuid), 1);
        }
        delete(entity);
    }

    @Override
    public void deleteAll() {
        em.createQuery(getDeleteAllQueryString()).executeUpdate();
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        for (T element : findAll()) {
            delete(element);
        }
    }

    @Transactional
    public T update(T t){
        T fromDB = findOne(t.getUuid());
        if(fromDB!=null){
            BeanUtils.copyProperties(t,fromDB);
        }
        return em.merge(fromDB);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void flush() {
        em.flush();
    }


    protected Class<T> getDomainClass() {
        return entityInformation.getJavaType();
    }

    private String getDeleteAllQueryString() {
        return getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName());
    }

    private String getCountQueryString() {
        String countQuery = String.format(COUNT_QUERY_STRING,"x", "%s");
        return getQueryString(countQuery, entityInformation.getEntityName());
    }

}
