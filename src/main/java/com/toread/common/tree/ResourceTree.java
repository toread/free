/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.common.tree;

import com.toread.core.domain.auth.rbac.Resource;

import java.util.List;

/**
 * @author lizhibing
 * @ClassName: ResourceService
 * @Description:
 * @date 2015-04-29 22:12
 */
public class ResourceTree  extends Tree<Resource>{
    public ResourceTree(List<Resource> treeList) {
        super(treeList);
    }
}
