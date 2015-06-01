/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.controller.atuh.rbac;

import com.toread.common.config.SessionConfig;
import com.toread.common.tree.ResourceTree;
import com.toread.common.tree.easyui.EasyUiNode;
import com.toread.core.controller.base.BaseController;
import com.toread.core.domain.auth.rbac.Account;
import com.toread.core.domain.auth.rbac.Resource;
import com.toread.core.service.auth.rbac.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lizhibing
 * @ClassName: ResourceController
 * @Description:
 * @date 2015-05-04 15:41
 */
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController<Resource>{

    @Override
    protected ResourceService realService() {
        return (ResourceService)baseService;
    }

    @ResponseBody
    @RequestMapping("/tree")
    public List<EasyUiNode> getResource(ModelMap model){
        ResourceTree resourceTree = new ResourceTree(realService().findAll());
        EasyUiNode rootNode = resourceTree.toEasyNode();
        return rootNode.getChildren();
    }

    @Override protected String getPageModelPath() {
        return null;
    }
}
