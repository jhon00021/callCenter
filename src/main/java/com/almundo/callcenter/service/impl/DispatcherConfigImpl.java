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
 * Created by gdxdeveloper1 on 2/08/18.
 */

@Service
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "clients")
public class DispatcherConfigImpl implements DispatcherConfig {

    private int maxconcurrentclient;

    private int maxclient;

    public void setMaxconcurrentclient(int maxconcurrentclient) {
        this.maxconcurrentclient = maxconcurrentclient;
    }

    public void setMaxclient(int maxclient) {
        this.maxclient = maxclient;
    }

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
