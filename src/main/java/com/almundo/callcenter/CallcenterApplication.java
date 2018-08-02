package com.almundo.callcenter;

import com.almundo.callcenter.service.Dispatcher;
import com.almundo.callcenter.service.DispatcherConfig;
import com.almundo.callcenter.service.StartCallCenter;
import com.almundo.callcenter.service.impl.DispatcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.PriorityBlockingQueue;

@SpringBootApplication
@ComponentScan(basePackages = "com.almundo.callcenter.service")
public class CallcenterApplication implements CommandLineRunner {

	@Autowired
	StartCallCenter start;

	@Autowired
	DispatcherConfig taskExecutor;




	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CallcenterApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) throws Exception {

		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);

		PriorityBlockingQueue queue = start.startCallCenterProcces();
		System.out.println(queue);

		DispatcherImpl dispatcher = (DispatcherImpl) context.getBean("dispatcherImpl");
		dispatcher.setPriorityBlockingQueue(queue);
		taskExecutor.taskExecutor().execute(dispatcher);

		DispatcherImpl dispatcher2 = (DispatcherImpl) context.getBean("dispatcherImpl");
		dispatcher2.setPriorityBlockingQueue(queue);
		taskExecutor.taskExecutor().execute(dispatcher2);
/*
		DispatcherImpl dispatcher3 = (DispatcherImpl) context.getBean("dispatcherImpl");
		dispatcher3.setName("JHON3");
		taskExecutor.taskExecutor().execute(dispatcher3);

		DispatcherImpl dispatcher4 = (DispatcherImpl) context.getBean("dispatcherImpl");
		dispatcher4.setName("4");
		taskExecutor.taskExecutor().execute(dispatcher4);*/


		for (;;) {
			int count = taskExecutor.taskExecutor().getActiveCount();
			System.out.println("Active Threads : " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				System.out.println(queue);
				taskExecutor.taskExecutor().shutdown();
				break;
			}
		}



	}

}
