package com.almundo.callcenter.impl;

import com.almundo.callcenter.service.GenerateQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateQueueTest  {

    @Autowired
    GenerateQueue queue;

    @Test
    public void validateCQueue(){
        assertThat(queue.generateQueueEmployees()).isNotNull();

    }

}

