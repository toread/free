/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.domain.auth.rbac;

import com.toread.core.domain.base.StateEntity;

import javax.persistence.Entity;

/**
 * @author lizhibing
 * @ClassName: Account
 * @Description:
 * @date 2015-05-04 15:45
 */
@Entity
public class Account extends StateEntity {
    private String code;
    private String pwd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
