/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.controller.atuh.rbac;

import com.toread.core.controller.base.BaseController;
import com.toread.core.domain.auth.rbac.User;
import com.toread.core.service.auth.rbac.AccountService;
import com.toread.core.service.auth.rbac.UserService;
import com.toread.core.service.base.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lizhibing
 * @ClassName: UserController
 * @Description:
 * @date 2015-05-04 15:41
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User>{
    @Override
    protected UserService realService() {
        return (UserService)baseService;
    }

    @Override protected String getPageModelPath() {
        return "/auth/rbac/user";
    }
}
