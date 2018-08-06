package com.almundo.callcenter.service;


import com.almundo.callcenter.model.Employee;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Jhon Velasquez
 */

public interface Dispatcher {

    /**
     * it sets the employees queue to the dispatch to know the employees free
     * @param queue the queue that was created with all the employees
     */
    void setPriorityBlockingQueue(PriorityBlockingQueue<Employee> queue);

    /**
     * this method dispatches each call
     */
    void dispatchCall();


}
