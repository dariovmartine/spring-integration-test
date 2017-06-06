package ar.com.directv.test.spring.integration.ws.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import ar.com.directv.test.spring.integration.ws.service.OrderService;

@Service
public class OrserServiceImpl implements OrderService {

	private static final java.util.Random rand = new java.util.Random();
	
	@Override
	public BigDecimal order(int itemId, int quantity) {
		
		BigDecimal amount = new BigDecimal(rand.nextDouble() * 1000 + rand.nextDouble() * 100);
		return amount;
				
	}
}
