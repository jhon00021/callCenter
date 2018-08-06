package com.almundo.callcenter.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by Jhon Velasquez
 */
public interface DispatcherConfig {


    /**
     * it creates the ThreadPool, and it has configutarion about how many call will attend at the same time,
     * and how many calls can get
     * @return the Thread poll whit its configuration
     */
    ThreadPoolTaskExecutor taskExecutor();
}
