package com.almundo.callcenter.service.impl;


import com.almundo.callcenter.service.DispatcherConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by Jhon Velasquez
 * This class has the employee queue control.
 * The variables are loaded using the value thah application.properties file contains
 */

@Service
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "clients")
public class DispatcherConfigImpl implements DispatcherConfig {

    /**
     * this field defines the max current client
     */
    private int maxconcurrentclient;

    /**
     * this field defines the max client
     */
    private int maxclient;

    public void setMaxconcurrentclient(int maxconcurrentclient) {
        this.maxconcurrentclient = maxconcurrentclient;
    }

    public void setMaxclient(int maxclient) {
        this.maxclient = maxclient;
    }

    /**
     * it creates the ThreadPool, and it has configutarion about how many call will attend at the same time,
     * and how many calls can get
     * @return the Thread poll whit its configuration
     */
    @Override
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(maxconcurrentclient);
        pool.setMaxPoolSize(maxclient);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
}
