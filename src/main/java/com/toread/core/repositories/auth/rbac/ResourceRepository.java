/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-28
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.repositories.auth.rbac;

import com.toread.core.domain.auth.rbac.Resource;
import com.toread.core.repositories.base.TreeRepository;
import com.toread.core.service.base.BaseService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author lizhibing
 * @ClassName: ResourceRepositories
 * @Description:
 * @date 2015-04-28 22:35
 */
@Repository
public class ResourceRepository extends TreeRepository<Resource> {

}
