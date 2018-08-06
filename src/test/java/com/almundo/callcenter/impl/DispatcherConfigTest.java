package com.almundo.callcenter.impl;

import com.almundo.callcenter.service.DispatcherConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatcherConfigTest {

    @Autowired
    private DispatcherConfig taskExecutor;

    @Test
    public void validateConf(){
        assertThat(taskExecutor.taskExecutor()).isNotNull();

    }


}
