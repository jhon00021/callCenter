package com.almundo.callcenter.service;


import com.almundo.callcenter.model.Employee;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */

public interface Dispatcher {

    void setPriorityBlockingQueue(PriorityBlockingQueue<Employee> queue);

    void dispatchCall();


}
