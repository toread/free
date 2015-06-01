package com.toread.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

/**
 * @author 探路者
 * @since 2014年-07月-12日
 */
public class ActivitiConfig {
    @Bean
    public ProcessEngine createProcessEngine(DataSource dataSource,JpaTransactionManager jpaTransactionManager) throws Exception {
        ProcessEngineFactoryBean engineFactoryBean = new ProcessEngineFactoryBean();
        engineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration(dataSource,jpaTransactionManager));
        return engineFactoryBean.getObject();
    }

    private SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource,JpaTransactionManager jpaTransactionManager){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(jpaTransactionManager);
        configuration.setJpaCloseEntityManager(true);
        configuration.setJpaHandleTransaction(true);

        configuration.setDatabaseSchemaUpdate(Boolean.TRUE.toString());
        configuration.setJobExecutorActivate(false);
        configuration.buildProcessEngine();
        return  configuration;
    }
}
