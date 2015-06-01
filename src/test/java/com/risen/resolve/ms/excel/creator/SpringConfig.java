/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-24
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.risen.resolve.ms.excel.creator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lizhibing
 * @ClassName: SpringConfig
 * @Description:
 * @date 2015-04-24 17:51
 */
@Configuration
@ComponentScan(basePackages = "com.toread.core")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {

}
