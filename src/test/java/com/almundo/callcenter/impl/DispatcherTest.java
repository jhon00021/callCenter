package com.almundo.callcenter.impl;

import com.almundo.callcenter.CallcenterApplication;
import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.Dispatcher;
import com.almundo.callcenter.service.DispatcherConfig;
import com.almundo.callcenter.service.GenerateQueue;
import com.almundo.callcenter.service.impl.DispatcherImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.PriorityBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatcherTest {

    @Autowired
    private GenerateQueue generateQueue;

    @Autowired
    private Dispatcher dispatcher;


    private PriorityBlockingQueue<Employee> queue;


    @Before
    public void init(){
        queue = generateQueue.generateQueueEmployees();
    }

    @Test
    public void validatequeuearteracall(){
        int sizeBeforeRun = queue.size();
        dispatcher.setPriorityBlockingQueue(queue);
        dispatcher.dispatchCall();

        assertThat(queue.size()).isEqualTo(sizeBeforeRun);

    }






}
