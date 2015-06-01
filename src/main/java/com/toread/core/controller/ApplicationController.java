/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-11
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.controller;

import com.toread.common.config.FreemarkerConfig;
import com.toread.common.config.SessionConfig;
import com.toread.core.domain.auth.rbac.Account;
import com.toread.core.service.auth.rbac.AccountService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhibing
 * @ClassName: ApplicationController
 * @Description:
 * @date 2015-04-11 15:53
 */
@Controller
@RequestMapping("public")
public class ApplicationController {
    @Autowired
    protected AccountService accountService;
    @RequestMapping("/index")
    public ModelAndView indexPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView(modelAndView,request);
    }

    @ResponseBody
    @RequestMapping(value ="/logIn")
    public Boolean logIn(Account user,HttpServletRequest request){
        Boolean validate = false;
        Account account = accountService.findByCodeAndPwd(user);
        if(account!=null){
            validate = true;
            request.getSession().setAttribute(SessionConfig.ACCOUNT_SESSION,account);
        }
        return validate;
    }

    protected ModelAndView modelAndView(ModelAndView modelAndView,HttpServletRequest request){
        String path =   request.getContextPath();
        modelAndView.getModel().put(FreemarkerConfig.ROOT_PATH,path);
        return modelAndView;
    }
}
