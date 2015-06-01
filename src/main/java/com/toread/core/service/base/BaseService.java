package com.toread.core.service.base;

import com.toread.core.domain.base.PrimaryKeyEntity;
import com.toread.core.repositories.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 封装基础数据访问
 */
public abstract class BaseService<T extends PrimaryKeyEntity> {
    @Autowired
    protected BaseRepository<T> baseRepository;

    protected abstract  <S extends BaseRepository<T>> S realRepository();

    public T findOne(String id){
        return baseRepository.findOne(id);
    }

    public List<T> findAll(){
        return baseRepository.findAll();
    }

    public Page<T> query(Pageable pageable,Map<String,Object> parameter){
        return baseRepository.query(pageable,parameter);
    }

    public List<T> findAll(List<String> ids){
        return baseRepository.findAll(ids);
    }

    public long count(){
        return baseRepository.count();
    }

    public List<T> save(List<T> entities){
        return baseRepository.save(entities);
    }

    public T save(T entity){
        return baseRepository.save(entity);
    }
    public T update(T entity){
        return baseRepository.update(entity);
    }
    boolean exists(String id){
        return baseRepository.exists(id);
    }

    public void delete(String id){
        baseRepository.delete(id);
    }

    public void delete(T entity){
        baseRepository.delete(entity);
    }

    public void delete(Iterable<T> entities){
        baseRepository.delete(entities);
    }

}