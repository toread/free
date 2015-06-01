/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-23
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.domain.auth.rbac;
import com.toread.core.domain.base.StateEntity;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author lizhibing
 * @ClassName: Role
 * @Description:
 * @date 2015-04-23 0:12
 */
@Entity
public class Role extends StateEntity{

    //角色名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
