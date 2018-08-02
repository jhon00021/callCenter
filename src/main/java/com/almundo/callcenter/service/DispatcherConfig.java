package com.almundo.callcenter.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */
public interface DispatcherConfig {

    ThreadPoolTaskExecutor taskExecutor();
}
