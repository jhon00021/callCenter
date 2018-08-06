package com.almundo.callcenter;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.DispatcherConfig;
import com.almundo.callcenter.service.GenerateQueue;
import com.almundo.callcenter.service.impl.DispatcherImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.concurrent.PriorityBlockingQueue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallcenterApplicationTests {

	@Autowired
	private GenerateQueue generateQueue;

	@Autowired
	private DispatcherConfig taskExecutor;

    ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);


    @Test
	public void with10Calls() {


		PriorityBlockingQueue<Employee> queue = generateQueue.generateQueueEmployees();
		int employeesSizeBefore = queue.size();


		//generates calls
		for(int i = 0; i<10; i++){
			DispatcherImpl dispatcher = (DispatcherImpl) context.getBean("dispatcherImpl");
			dispatcher.setPriorityBlockingQueue(queue);
			taskExecutor.taskExecutor().execute(dispatcher);
		}

		for (;;) {
			int count = taskExecutor.taskExecutor().getActiveCount();

			if (count == 0) {
				taskExecutor.taskExecutor().shutdown();
				break;
			}
		}
        //it should not have theads alive
		assertThat(taskExecutor.taskExecutor().getActiveCount()).isEqualTo(0);


        assertThat(queue.size()).isEqualTo(employeesSizeBefore);

    }

    @Test
    public void with20Calls() {
        PriorityBlockingQueue<Employee> queue = generateQueue.generateQueueEmployees();
        int employeesSizeBefore = queue.size();
        taskExecutor = (DispatcherConfig) context.getBean("dispatcherConfigImpl");


        //generates calls
        for(int i = 0; i<20; i++){
            DispatcherImpl dispatcher = (DispatcherImpl) context.getBean("dispatcherImpl");
            dispatcher.setPriorityBlockingQueue(queue);
            taskExecutor.taskExecutor().execute(dispatcher);
        }

        for (;;) {
            int count = taskExecutor.taskExecutor().getActiveCount();

            if (count == 0) {
                taskExecutor.taskExecutor().shutdown();
                break;
            }
        }
        //it should not have theads alive
        assertThat(taskExecutor.taskExecutor().getActiveCount()).isEqualTo(0);

        assertThat(queue.size()).isEqualTo(employeesSizeBefore);

    }

}
