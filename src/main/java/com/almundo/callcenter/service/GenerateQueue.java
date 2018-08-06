package com.almundo.callcenter.service;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Jhon Velasquez
 */
public interface GenerateQueue {

    /**
     * this method creates the employee queue
     * @return a PriorityBlockingQueue with employees and each employee has its own level (OPERATOR, SUPERVISOR or COORDINATOR)
     */
    PriorityBlockingQueue generateQueueEmployees();

}
