/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-27
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.domain.auth.rbac;

import com.toread.common.tree.easyui.annotation.EasyUiTreeIcon;
import com.toread.common.tree.easyui.annotation.EasyUiTreeText;
import com.toread.core.domain.base.tree.TreeEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author lizhibing
 * @ClassName: Resource
 * @Description:
 * @date 2015-04-27 21:52
 */
@Entity
public class Resource extends TreeEntity{
    private String url;
    @EasyUiTreeText
    private String name;
    @EasyUiTreeIcon
    private String icon;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
