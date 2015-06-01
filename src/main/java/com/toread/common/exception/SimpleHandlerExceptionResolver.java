package com.toread.common.exception;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 探路者
 * @since 2014年-09月-03日 02:13
 */
public class SimpleHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            outputMessage.getBody().write("传奇".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
