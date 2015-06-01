/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.service.auth.rbac;

import com.toread.core.domain.auth.rbac.Role;
import com.toread.core.repositories.auth.rbac.ResourceRepository;
import com.toread.core.repositories.auth.rbac.RoleRepository;
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
public class RoleService extends BaseService<Role> {
    @Override
    protected RoleRepository realRepository() {
        return (RoleRepository)baseRepository;
    }
}
