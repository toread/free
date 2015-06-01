package com.toread.core.controller.base;

import com.toread.common.page.EasyUiPage;
import com.toread.common.page.PageResultType;
import com.toread.config.MvcConfig;
import com.toread.core.domain.base.PrimaryKeyEntity;
import com.toread.core.repositories.base.BaseRepository;
import com.toread.core.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * 对于BaseController来说拥有具备以下功能
 * <p>RestFul风格的请求</p>
 * <ul>
 *     <li>GET:从指定URL获取内容</li>
 *     <li>POST:创建内容到指定的url中</li>
 *     <li>PUT:更新内容到指定的url中</li>
 *     <li>DELETE:从指定url中删除数据</li>
 * </ul>
 *
 * @author 探路者
 * @see 2014年9月3日01:00:50
 */
public abstract class BaseController<T extends PrimaryKeyEntity>{
    protected BaseService<T> baseService;
    protected abstract  <C extends BaseService<T>> C realService();


    /**
     *获取当前controller的的根路径
     * @return
     */
    protected abstract String getPageModelPath();

    /****************************** 以下方法是对RestFul规范定义 ********************************/
    @Autowired
    public void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public T save(@Valid T t,Errors errors){
        return  baseService.save(t);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST)
    @ResponseBody
    public T update(T t){
        return  baseService.update(t);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void  delete(@PathVariable String id){
        baseService.delete(id);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public T  get(@PathVariable String id){
        return  baseService.findOne(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<T> listPage(@PageableDefault(
            page = 0, size =20) Pageable pageable,@RequestParam Map parameterMap){
        return baseService.query(pageable,parameterMap);
    }

    /****************************** 以下方法是对页面结构规范定义 ********************************/
    @RequestMapping(value = "/"+PageResultType.FORM_STRING,method = RequestMethod.GET)
    public <Q extends PrimaryKeyEntity> ModelAndView  form(T t,@RequestParam Map parameterMap){
        return new ModelAndView(freemarkerPath(PageResultType.FORM));
    }

    @RequestMapping(value = "/"+PageResultType.GRID_STRING,method = RequestMethod.GET)
    public <Q extends PrimaryKeyEntity> ModelAndView  grid(T t,@RequestParam Map parameterMap){
        return new ModelAndView(freemarkerPath(PageResultType.GRID));
    }

    protected String freemarkerPath(PageResultType pageResultType){
        String rootPath = getPageModelPath();
        String modelName = rootPath.substring(rootPath.lastIndexOf("/"),rootPath.length());
        StringBuffer freeMarkerPathBuffer = new StringBuffer();
        freeMarkerPathBuffer.append(rootPath).
                append(modelName).append("-").append(PageResultType.getType(pageResultType));
        return freeMarkerPathBuffer.toString();
    }
}
