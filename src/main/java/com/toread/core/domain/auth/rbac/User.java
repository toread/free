/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-27
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.domain.auth.rbac;

import com.toread.core.domain.base.StateEntity;
import com.toread.core.domain.base.tree.TreeEntity;

import javax.persistence.Entity;
import java.util.List;

/**
 * @ClassName: ResourceHolder
 * @Description:
 * @date 2015-04-27 15:52
 */
@Entity
public class User extends StateEntity{
    private String name;
    private String sex;
    private String address;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
