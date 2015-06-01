/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.service.auth.rbac;

import com.toread.core.domain.auth.rbac.Account;
import com.toread.core.domain.auth.rbac.Role;
import com.toread.core.domain.auth.rbac.User;
import com.toread.core.repositories.auth.rbac.UserRepository;
import com.toread.core.repositories.base.BaseRepository;
import com.toread.core.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author lizhibing
 * @ClassName: RoleService
 * @Description:
 * @date 2015-04-29 17:22
 */
@Service
public class UserService extends BaseService<User> {
    @Override
    protected UserRepository realRepository() {
        return (UserRepository)baseRepository;
    }
}
