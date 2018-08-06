package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Level;
import com.almundo.callcenter.service.GenerateQueue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Jhon Velasquez
 * This class has the employee queue control.
 * The variables are loaded using the value thah application.properties file contains
 */
@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "queue")
public class GenerateQueueImpl implements GenerateQueue {

    /**
     * this field defines the max operators that tha queue will have
     */
    private int maxoperator;

    /**
     * this field defines the max supervisors that tha queue will have
     */
    private int maxsupervisor;

    /**
     * this field defines the max directors that tha queue will have
     */
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

    /**
     * this method creates the employee queue
     * @return a PriorityBlockingQueue with employees and each employee has its own level (OPERATOR, SUPERVISOR or COORDINATOR)
     */
    @Override
    public PriorityBlockingQueue generateQueueEmployees() {
        PriorityBlockingQueue<Employee> queue = new PriorityBlockingQueue<>();
        createQueue(queue);
        return queue;

    }

    /**
     * this Method creates the employee queue according the max operators, supervisors and directors configurated
     * @param queue it is  queue that will be loaded by reference.
     */
    private void createQueue(PriorityBlockingQueue<Employee> queue) {

        //creating operators
        for(int i = 0; i< maxoperator; i++)
            queue.add(new Employee("OPERATOR "+(i+1),Level.OPERATOR));

        //creating SUPERVISOR
        for(int i = 0; i< maxsupervisor; i++)
            queue.add(new Employee("SUPERVISOR "+(i+1),Level.SUPERVISOR));

        //creating DIRECTOR
        for(int i = 0; i< maxdirector; i++)
            queue.add(new Employee("DIRECTOR "+(i+1),Level.DIRECTOR));

    }
}

