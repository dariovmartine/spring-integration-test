package ar.com.directv.test.spring.integration.si;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.directv.test.spring.integration.types.ProcessOrderRequest;

public class ServiceActivator {
	
	static final Logger logger = LoggerFactory.getLogger(ServiceActivator.class);
	
	public ProcessOrderRequest processOrder(Order order) {
		
		logger.info("Recibo: {} ", order  );
		
		ProcessOrderRequest request = new ProcessOrderRequest();
		request.setItemId(BigInteger.valueOf(order.getItems().get(0).getItemId()));
		request.setQuantity(BigInteger.valueOf(order.getItems().get(0).getQuantity()));
		
		return request;
	}

}
