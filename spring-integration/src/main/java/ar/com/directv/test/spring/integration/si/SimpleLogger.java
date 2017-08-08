package ar.com.directv.test.spring.integration.si;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.directv.test.spring.integration.types.ProcessOrderResponse;

public class SimpleLogger {
	
	static final Logger logger = LoggerFactory.getLogger(SimpleLogger.class);
	
	public static int i = 0;
	public void logProcessedOrder(ProcessOrderResponse order) {
		
		logger.info("Recibo: {} count: {}", order.getAmount() ,++i );
		
	}


}
