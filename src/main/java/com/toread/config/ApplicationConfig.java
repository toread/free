package com.toread.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 14-1-12.
 */
@Import({MvcConfig.class,RepositoriesConfig.class,
//        CacheConfig.class
//        ,ActivitiConfig.class
})

@ComponentScan(basePackages = "com.toread")
public class ApplicationConfig {

}
