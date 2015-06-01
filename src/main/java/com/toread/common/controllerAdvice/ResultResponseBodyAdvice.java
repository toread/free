package com.toread.common.controllerAdvice;

import com.toread.core.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 探路者
 * @since 2014年-09月-05日 16:31
 */
@ControllerAdvice(basePackages = "com.toread.core.controller")
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    protected  final  static  Logger LOGGER = LoggerFactory.getLogger(ResultResponseBodyAdvice.class);
    @Override
    /**
     * 过滤是否是json的数据
     */
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        Boolean isRest = AnnotationUtils.isAnnotationDeclaredLocally(RestController.class, methodParameter.getContainingClass());
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(methodParameter.getMethod(),RequestMapping.class);
        if(isRest||requestMapping!=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 修改返回的数据
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if(!(o instanceof Result)){
            Result result = Result.getInstance();
            result.setResult(o);
            o = result;
        }
        return o;
    }

    /**
     * 全局异常的支持
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error(ex.getMessage(),ex);Result response = new Result();
        response.setSuccess(false);
        response.setMsg("操作失败");
        return response;
    }

    public static class Result{
        private String msg;
        private Boolean success;
        private Object result;

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public  static Result getInstance(){
            Result result  = new Result();
            result.setSuccess(true);
            result.setMsg("操作成功");
            return result;
        }
    }

}
