package com.almundo.callcenter;

import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.service.DispatcherConfig;
import com.almundo.callcenter.service.GenerateQueue;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@ComponentScan(basePackages = "com.almundo.callcenter.service")
public class CallcenterApplication implements CommandLineRunner {

	@Autowired
	GenerateQueue generateQueue;

	@Autowired
	DispatcherConfig taskExecutor;

	private final Logger logger = LogManager.getLogger(CallcenterApplication.class);



	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CallcenterApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info("run: procces started");

		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);
		PriorityBlockingQueue<Employee> queue = generateQueue.generateQueueEmployees();
		logger.info("run: Employee queue got it, with information "+ queue);



		for(int i = 0; i<11; i++){
			DispatcherImpl dispatcher = (DispatcherImpl) context.getBean("dispatcherImpl");
			dispatcher.setPriorityBlockingQueue(queue);
			taskExecutor.taskExecutor().execute(dispatcher);
		}

		for (;;) {
			int count = taskExecutor.taskExecutor().getActiveCount();
			logger.info("run: Active Threads : " + count);
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
