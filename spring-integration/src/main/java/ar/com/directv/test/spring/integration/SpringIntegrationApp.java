package ar.com.directv.test.spring.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIntegrationApp {

	
	public static  void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-async-ws-usingrest.xml");

	}
}
