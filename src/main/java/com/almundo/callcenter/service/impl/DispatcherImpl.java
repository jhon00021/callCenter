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
 * Created by Jhon Velasquez
 * This class controls the calls and dispatches to a employee free the call,
 * when the employee finishes it will be available to attend another call
 * Each call starts will be dispatches for a new Thread
 * The variables are loaded using the value thah application.properties file contains
 */

@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "dispatchcall")
public class DispatcherImpl implements Dispatcher,Runnable {

    private final Logger logger = LogManager.getLogger(DispatcherImpl.class);

    /**
     * it is the employee queue
     */
    private PriorityBlockingQueue<Employee> queue;

    /**
     * this field defines the min time that the call will have
     */
    private int mintime;

    /**
     * this field defines the max time that the call will have
     */
    private int maxtime;

    public void setMintime(int mintime) {
        this.mintime = mintime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }

    /**
     * it sets the employees queue to the dispatch to know the employees free
     * @param queue the queue that was created with all the employees
     */
    @Override
    public void setPriorityBlockingQueue(PriorityBlockingQueue<Employee> queue){
        this.queue = queue;
    }

    /**
     * this method dispatches each call, when the call starts, an employee will take it
     * the employees queue is order by employee level, the queue has the employees available,
     * the employee that takes the call, will not be able to take another until it finishes
     * if some call arrives and there is not available employee, the queue will block the procces until an employee be available
     * it simulates a duration of the call
     */
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

    /*+
     * this method starts a new thread for each call
     */
    @Override
    public void run() {
        dispatchCall();
    }
}
