/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-03
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.common.tree;

import java.util.List;

/**
 * @ClassName: TreeNode
 * @Description:
 * @author lizhibing
 * @date 2015-04-03 11:56 
 */
public class TreeNode<T> {
    @SuppressWarnings("rawtypes")
    protected TreeNode father;
    protected List<TreeNode<T>> childes;
    protected T data;

    @SuppressWarnings("rawtypes")
    public TreeNode getFather() {
        return father;
    }

    @SuppressWarnings("rawtypes")
    public void setFather(TreeNode father) {
        this.father = father;
    }

    public List<TreeNode<T>> getChildes() {
        return childes;
    }

    public void setChildes(List<TreeNode<T>> childes) {
        this.childes = childes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
