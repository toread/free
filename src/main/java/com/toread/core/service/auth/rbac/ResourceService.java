/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-27
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.service.auth.rbac;

import com.toread.core.domain.auth.rbac.Resource;
import com.toread.core.domain.base.tree.TreeEntity;
import com.toread.core.repositories.auth.rbac.AccountRepository;
import com.toread.core.repositories.auth.rbac.ResourceRepository;
import com.toread.core.repositories.base.BaseRepository;
import com.toread.core.service.base.TreeEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhibing
 * @ClassName: Resource
 * @Description:
 * @date 2015-04-27 21:54
 */
@Service
public class ResourceService extends TreeEntityService<Resource> {

    @Override
    protected ResourceRepository realRepository() {
        return (ResourceRepository)baseRepository;
    }
}
