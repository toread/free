/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-03
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.common.tree;

import com.toread.common.tree.easyui.*;
import com.toread.common.tree.easyui.annotation.*;
import com.toread.core.domain.base.tree.TreeEntity;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @ClassName: Tree
 * @Description:
 * @author lizhibing
 * @date 2015-04-03 11:55 
 */
public class Tree<T extends TreeEntity> {
    private Class<T> tClass;
    protected TreeNode<T> root;
    protected List<T> treeList;

    @SuppressWarnings("rawtypes")
    public TreeNode getRoot() {
        return root;
    }

    public Tree(List<T> treeList){
        this.treeList = treeList;
        this.root = this.searchRootNode(treeList);
        this.build(root);
        this.getTClass();
    }

    protected void build(TreeNode<T> root){
        searchAndBuildChildes(root);
    }

    protected void searchAndBuildChildes(TreeNode<T> t){
        List<TreeNode<T>> childNodes = this.searchChildes(t,treeList);
        t.setChildes(childNodes);
        if(!CollectionUtils.isEmpty(childNodes)){
            for (TreeNode<T> childNode : childNodes) {
                searchAndBuildChildes(childNode);
            }
        }
    }

    protected  List<TreeNode<T>> searchChildes(TreeNode<T> treeNode,List<T> treeList){
        List<TreeNode<T>> treeNodeList  = new ArrayList<TreeNode<T>>();
        for (T item : treeList) {
            if(treeNode.getData().getUuid().equals(item.getParentUuid())){
                TreeNode<T> itemTreeNode = new TreeNode<T>();
                itemTreeNode.setData(item);
                itemTreeNode.setFather(treeNode);
                treeNodeList.add(itemTreeNode);
            }
        }
        return treeNodeList;
    }

    protected  TreeNode<T> searchRootNode(List<T> treeList){
        TreeNode<T> positionTreeNode = null;
        for (T position1 : treeList) {
            if(position1.getParentUuid()==null){
                positionTreeNode  = new TreeNode<T>();
                positionTreeNode.setData(position1);
                break;
            }
        }
        Assert.notNull(positionTreeNode,"根节点ROOT未找到");
        return positionTreeNode;
    }

    protected void getTClass() {
        this.tClass =  (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EasyUiNode toEasyNode(){
        EasyUiNode easyUiNode = null;
        if(root!=null){
            easyUiNode = convertNode(root.getData());
            buildEasyUiNode(easyUiNode,root);
        }
        return easyUiNode;
    }

    protected void buildEasyUiNode(EasyUiNode easyUiNode,TreeNode<T> t){
        List<TreeNode<T>> data = t.getChildes();
        if(!CollectionUtils.isEmpty(data)){
            List<EasyUiNode>  children = new ArrayList<>();
            for (TreeNode<T> tTreeNode : data) {
                EasyUiNode easyUiChildNode = convertNode(t.getData());
                if(easyUiChildNode!=null){
                    children.add(easyUiChildNode);
                }
                buildEasyUiNode(easyUiChildNode,tTreeNode);
            }
            easyUiNode.setChildren(children);
        }
    }

    protected EasyUiNode convertNode(T t){
        if(t == null){return null;}
        EasyUiNode easyUiNode = new EasyUiNode();
        Set<Field> fields= new HashSet<Field>();
        Class<?> Class = tClass;
        for (;!Class.equals(Object.class);Class = Class.getSuperclass()) {
            fields.addAll(Arrays.asList(Class.getDeclaredFields()));
        }
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value  = PropertyUtils.getProperty(t,field.getName());
                if(field.isAnnotationPresent(EasyUiTreeId.class)){
                    easyUiNode.setId(value);
                }else if(field.isAnnotationPresent(EasyUiTreeText.class)){
                    easyUiNode.setText(value);
                }else if(field.isAnnotationPresent(EasyUiTreeChecked.class)){
                    easyUiNode.setChecked(value);
                }else if(field.isAnnotationPresent(EasyUiTreeState.class)){
                    easyUiNode.setState(value);
                }else if(field.isAnnotationPresent(EasyUiTreeIcon.class)){
                    easyUiNode.setIconCls(value);
                }else{
                    easyUiNode.getAttributes().put(field.getName(),value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return easyUiNode;
    }
}
