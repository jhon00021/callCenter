package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.Dispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */

@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "dispatchcall")
public class DispatcherImpl implements Dispatcher,Runnable {

    PriorityBlockingQueue<Employee> queue;

    private final Logger logger = LogManager.getLogger(DispatcherImpl.class);

    private int mintime;

    private int maxtime;

    public void setMintime(int mintime) {
        this.mintime = mintime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }

    @Override
    public void setPriorityBlockingQueue(PriorityBlockingQueue<Employee> queue){
        this.queue = queue;
    }

    @Override
    public void dispatchCall() {
        try {
            Employee take = queue.take();
            logger.info("dispatchCall:+ "+ take.getName() + " is busy");
            int callDurationMinute = (int) (Math.random() * mintime + 1) +(maxtime - mintime);
            long callDurationSec = callDurationMinute * 1000;
            logger.info("dispatchCall:+ "+ take.getName() + " time of call ..." + callDurationSec);
            Thread.sleep(callDurationSec);
            logger.info("dispatchCall:+ "+ take.getName() + " is free");
            queue.add(take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        dispatchCall();
    }
}
