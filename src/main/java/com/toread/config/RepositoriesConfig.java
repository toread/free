package com.toread.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.toread.common.util.Log4JdbcCustomFormatterExt;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.LoggingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Administrator on 14-1-15.
 */
@EnableTransactionManagement
@PropertySource(value = "classpath:jdbc.properties")
public class RepositoriesConfig {
    @Autowired
    private Environment env;

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("com.toread.core");
        return lemfb;
    }

    public static DataSource addLogProxy(DataSource dataSource){
        Log4jdbcProxyDataSource proxyDataSource = new Log4jdbcProxyDataSource(dataSource);
        proxyDataSource.setLogFormatter(createLog4jLoggerFormatter());
        return proxyDataSource;
    }

    private static Log4JdbcCustomFormatterExt createLog4jLoggerFormatter(){
        Log4JdbcCustomFormatterExt log4JdbcCustomFormatter = new Log4JdbcCustomFormatterExt();
        log4JdbcCustomFormatter.setLoggingType(LoggingType.MULTI_LINE);
        log4JdbcCustomFormatter.setMargin(12);
        log4JdbcCustomFormatter.setSqlPrefix("sql语句:");
        return log4JdbcCustomFormatter;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        dataSource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("jdbc.testWhileIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("jdbc.maxWait")));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
        //添加日志记录器
        return RepositoriesConfig.addLogProxy(dataSource);
    }
}









