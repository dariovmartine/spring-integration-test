package ar.com.directv.test.spring.integration.ws.service;

import java.math.BigDecimal;

public interface OrderService {

	public BigDecimal order(int itemId, int quantity);
}
