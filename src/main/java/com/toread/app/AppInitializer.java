package com.toread.app;

import com.toread.config.ApplicationConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.*;
import java.util.EnumSet;


/**
 * Created by Administrator on 14-1-12.
 */
public class AppInitializer implements WebApplicationInitializer {
    /**** 启动设置 ****/
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String UTF_8_ENCODE ="UTF-8";
    private static final String CHARACTER_FILTER ="CharacterFilter";
    private static final String LOG4J_CONFIG ="log4jConfigLocation";
    private static final String LOG4J_CONFIG_LOCATION ="/WEB-INF/log4j.properties";

    /**** 运行环境 ****/
    private static final String ENVIRONMENT_PRODUCT ="Product";
    private static final String ENVIRONMENT_DEVELOPMENT ="Development";



    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("readonly","false");
        addLoger4jListener(servletContext);
        AnnotationConfigWebApplicationContext webApplicationContext = getAnnotationConfigWebApplicationContext(servletContext);
        addCharacterFilter(servletContext);
        springMvcDispatcherServlet(servletContext, webApplicationContext);
        registerHttPutFormContentFilter(servletContext);
    }

    /**
     * 浏览器form表单只支持GET与POST请求，而DELETE、PUT等method并不支持，spring3.0添加了一个过滤器，<br/>
     * 可以将这些请求转换为标准的http方法，使得支持GET、POST、PUT与DELETE请求，<br/>
     * 该过滤器为HiddenHttpMethodFilter。<br/>
     * 不建议使用这个 因为他需要传递一个隐藏的input[name="_method"] value="put"
     * @param servletContext
     */
    private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration;
        registration = servletContext.addFilter("hiddenHttpMethodFilter",
                HiddenHttpMethodFilter.class);
        registration.addMappingForServletNames(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
                false,DISPATCHER_SERVLET_NAME
        );
    }


    /**
     *这个也可以转换为标准的http请求
     * 他通过ajax type类型来确定
     */
    private void registerHttPutFormContentFilter(
            ServletContext servletContext) {
        FilterRegistration.Dynamic registration;
        registration = servletContext.addFilter("httpPutFormContentFilter",HttpPutFormContentFilter.class);
        registration.addMappingForServletNames(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
                false,DISPATCHER_SERVLET_NAME
        );
    }

    private void addLoger4jListener(final ServletContext servletContext) {
        servletContext.addListener(Log4jConfigListener.class);
        servletContext.setAttribute(LOG4J_CONFIG, LOG4J_CONFIG_LOCATION);
    }


    private AnnotationConfigWebApplicationContext getAnnotationConfigWebApplicationContext(final ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext =  new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(ApplicationConfig.class);
        //添加环境参数
        webApplicationContext.getEnvironment().setActiveProfiles(ENVIRONMENT_PRODUCT);
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        return webApplicationContext;
    }

    private void springMvcDispatcherServlet(final ServletContext servletContext, AnnotationConfigWebApplicationContext webApplicationContext) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(webApplicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("detectAllHandlerExceptionResolvers",Boolean.FALSE.toString());
    }

    public void addCharacterFilter(ServletContext servletContext){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(UTF_8_ENCODE);
        FilterRegistration.Dynamic  encodingFilterDispatcher =  servletContext.addFilter(CHARACTER_FILTER,encodingFilter);
        encodingFilterDispatcher.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true,new String[]{"/*"});
    }
}
