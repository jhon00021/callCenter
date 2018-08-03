package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Level;
import com.almundo.callcenter.service.GenerateQueue;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */
@Service
public class GenerateQueueImpl implements GenerateQueue {


    @Override
    public PriorityBlockingQueue generateQueueEmployees() {
        PriorityBlockingQueue<Employee> queue = new PriorityBlockingQueue<>();
        createQueue(queue);
        return queue;

    }

    private void createQueue(PriorityBlockingQueue<Employee> queue) {


        //creating operators
        for(int i = 0; i< 5; i++)
            queue.add(new Employee("Juan "+(i+1),Level.OPERATOR));

        //creating SUPERVISOR
        for(int i = 0; i< 3; i++)
            queue.add(new Employee("Jhon "+(i+1),Level.SUPERVISOR));

        //creating DIRECTOR
        for(int i = 0; i< 2; i++)
            queue.add(new Employee("Sebastian "+(i+1),Level.DIRECTOR));


       // return queue;

    }
}

