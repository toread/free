package com.toread.core.service.base;

import com.toread.core.domain.base.tree.TreeEntity;
import com.toread.core.repositories.base.BaseRepository;
import com.toread.core.repositories.base.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2014-11-04.
 */
public abstract  class TreeEntityService<T extends TreeEntity> extends StateService<T>{
    @Autowired
    protected TreeRepository<T> treeRepository;

    /**
     * 获取子节点
     * @return
     */
    public List<T> findChildes(T resource){
        return treeRepository.findChildes(resource);
    }

    /**
     * 获取父节点
     * @param t
     * @return
     */
    public T findFather(T t){
        return treeRepository.findFather(t);
    }


    /**
     * 添加子节点
     * @param father
     * @param child
     * @return
     */
    public void addChild(T father,T child){
        treeRepository.addChild(father, child);
    }

    Integer deleteChild(T child){
        return treeRepository.deleteChild(child);
    }
}
