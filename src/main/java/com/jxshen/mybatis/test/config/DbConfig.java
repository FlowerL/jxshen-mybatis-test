package com.jxshen.mybatis.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库配置类
 *
 * @author jxshen on 2018/01/18
 */
@Slf4j
@Configuration
public class DbConfig implements EnvironmentAware {

    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setMaxActive(100);
        dataSource.setMaxWait(10000);
        dataSource.setDbType("mysql");
        dataSource.setConnectionErrorRetryAttempts(1); // 连接出错重试次数
        dataSource.setBreakAfterAcquireFailure(true); // 连接出错时重试N次后退出
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setTimeBetweenEvictionRunsMillis(3000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(false);
        try {
            dataSource.setFilters("stat,config");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
            bean.setConfigLocation(resolver.getResource("classpath:SQLMapConfig.xml"));
            return bean.getObject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.jxshen.mybatis.test.dao");
        return mapperScannerConfigurer;
    }

}
