package ar.com.directv.test.spring.integration.si;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIntegrationApp {

	
	public static  void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	}
}
