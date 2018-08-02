package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.Dispatcher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
        run();

    }

    @Override
    public void run() {


        try {
            Employee take = queue.take();
            System.out.println(take.getName() + " is running");
            Thread.sleep(5000);
            System.out.println(take.getName() + " finished");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
