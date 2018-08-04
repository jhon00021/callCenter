package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Level;
import com.almundo.callcenter.service.GenerateQueue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */
@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "queue")
public class GenerateQueueImpl implements GenerateQueue {

    private int maxoperator;

    private int maxsupervisor;

    private int maxdirector;

    public void setMaxoperator(int maxoperator) {
        this.maxoperator = maxoperator;
    }

    public void setMaxsupervisor(int maxsupervisor) {
        this.maxsupervisor = maxsupervisor;
    }

    public void setMaxdirector(int maxdirector) {
        this.maxdirector = maxdirector;
    }

    @Override
    public PriorityBlockingQueue generateQueueEmployees() {
        PriorityBlockingQueue<Employee> queue = new PriorityBlockingQueue<>();
        createQueue(queue);
        return queue;

    }

    private void createQueue(PriorityBlockingQueue<Employee> queue) {


        //creating operators
        for(int i = 0; i< maxoperator; i++)
            queue.add(new Employee("Juan "+(i+1),Level.OPERATOR));

        //creating SUPERVISOR
        for(int i = 0; i< maxsupervisor; i++)
            queue.add(new Employee("Jhon "+(i+1),Level.SUPERVISOR));

        //creating DIRECTOR
        for(int i = 0; i< maxdirector; i++)
            queue.add(new Employee("Sebastian "+(i+1),Level.DIRECTOR));

    }
}

