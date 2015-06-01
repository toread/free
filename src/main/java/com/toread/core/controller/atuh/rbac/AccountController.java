/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.controller.atuh.rbac;

import com.toread.common.config.SessionConfig;
import com.toread.core.controller.base.BaseController;
import com.toread.core.domain.auth.rbac.Account;
import com.toread.core.service.auth.rbac.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhibing
 * @ClassName: UserController
 * @Description:
 * @date 2015-05-04 15:41
 */
@Controller
@RequestMapping("account")
public class AccountController extends BaseController<Account>{
    @Override
    protected AccountService realService() {
        return (AccountService)baseService;
    }

    @Override protected String getPageModelPath() {
        return null;
    }
}
