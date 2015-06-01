/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-24
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.risen.resolve.ms.excel.creator;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lizhibing
 * @ClassName: SpringLean
 * @Description:
 * @date 2015-04-24 17:48
 */
public class SpringLean {
    @Test
    public void test(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
