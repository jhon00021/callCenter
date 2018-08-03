package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.Dispatcher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */

@Component
@Scope("prototype")
public class DispatcherImpl implements Dispatcher,Runnable {

    PriorityBlockingQueue<Employee> queue;

    @Override
    public void setPriorityBlockingQueue(PriorityBlockingQueue<Employee> queue){
        this.queue = queue;
    }

    @Override
    public void dispatchCall() {

        try {
            Employee take = queue.take();
            System.out.println(take.getName() + " is busy");
            int callDurationMinute = (int) (Math.random() * 6) +5;
            long callDurationMilSec = callDurationMinute * 1000;
            System.out.println(take.getName() + " time of call ..." + callDurationMilSec);
            Thread.sleep(callDurationMilSec);
            System.out.println(take.getName() + " is free");
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
