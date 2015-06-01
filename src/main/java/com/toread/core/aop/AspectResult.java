package com.toread.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

/**
 * 这个目前被废弃了
 * @author 探路者
 * @since 2014年-09月-03日 04:25
 */
public class AspectResult{
    private  static Logger logger = Logger.getLogger(AspectResult.class);

    /**
     * Pointcut是方法级别
     * 注解
     * pattern分别表示修饰符匹配（modifier-pattern?）、
     * 返回值匹配（ret-type-pattern）、
     * 类路径匹配（declaring-type-pattern?）、
     * 方法名匹配（name-pattern）、
     * 参数匹配（(param-pattern)）、
     * 异常类型匹配（throws-pattern?）
     */
    @Pointcut("execution(public * com.toread.core.controller.*.*(..))")
    public void allController(){}


    /**
     *
     * @param jp
     * @return
     * @throws Throwable
     * Spring 拦截controller层不是那么好高的
     * 最起码有冲突的地方、因为spring本省有拦截器
     */
    @Around("allController()")
    public Object changeReturn(ProceedingJoinPoint jp) throws Throwable {
        StopWatch clock = new StopWatch("检测业务方法调用"+jp.toShortString());
        Object object = null;
        try {
            clock.start(jp.toShortString());
            object = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            clock.stop();
            if(logger.isDebugEnabled()){
                logger.debug(clock.prettyPrint());
            }
        }
        return object;
    }
}
