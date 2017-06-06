package ar.com.directv.test.spring.integration.ws.endpoint;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ar.com.directv.test.spring.integration.ws.service.OrderService;
import ar.com.directv.test.spring.integration.ws.types.ProcessOrderRequest;
import ar.com.directv.test.spring.integration.ws.types.ProcessOrderResponse;

@Endpoint
public class ProcessOrderEndpoint {
	

	@Autowired
	private OrderService orderService;
	
	@PayloadRoot(localPart="processOrderRequest", namespace="http://www.test.spring.integration.ws/orders")
	public @ResponsePayload ProcessOrderResponse order(@RequestPayload ProcessOrderRequest order) throws InterruptedException {
				
		BigDecimal amount = orderService.order(order.getItemId().intValue(), order.getQuantity().intValue());
		ProcessOrderResponse response = new ProcessOrderResponse();
		response.setAmount(amount.setScale(2, RoundingMode.DOWN));
		
		return response;
	}
	
	
}
