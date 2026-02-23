package com.tejait.batch15;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class Batch15Application {
	
	
	private static final Logger logger=LogManager.getLogger(Batch15Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Batch15Application.class, args);
 		logger.debug("Debug Level"); // development stage
 		logger.info("Info Level"); // Production Stage..
 		logger.warn("Warn Level"); // to show Warning msgs
 		logger.error("Error Level"); // To show Error msgs
 		logger.fatal("Fatal Level"); // To show Server db issues logs
 		//logger.trace("Trace Level"); // To show trace(each stage) logs
 		
 		
		
		
	}

	
}



 