/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-05
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.common.tree.easyui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EasyuiNode
 * @Description:
 * @author lizhibing
 * @date 2015-05-05 22:54 
 */
public class EasyUiNode {
    private Object id;
    private Object text;
    private Object state;
    private Object checked;
    private Object iconCls;
    public Object getIconCls() {
        return iconCls;
    }

    public void setIconCls(Object iconCls) {
        this.iconCls = iconCls;
    }

    private Map<String,Object> attributes = new HashMap<>(8);
    private List<EasyUiNode>  children;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getChecked() {
        return checked;
    }

    public void setChecked(Object checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<EasyUiNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUiNode> children) {
        this.children = children;
    }
}
