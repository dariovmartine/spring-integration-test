package ar.com.directv.test.spring.integration.nio;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;

import ar.com.directv.test.spring.integration.types.OrdersPort;
import ar.com.directv.test.spring.integration.types.ProcessOrderRequest;
import ar.com.directv.test.spring.integration.types.ProcessOrderResponse;

public class NIOServiceActivator implements ApplicationContextAware  {
	
	private ApplicationContext context;

	OrdersPort mySoapService;
	
	public void setUri(String uri) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(OrdersPort.class);
		factory.setAddress(uri);
		mySoapService = (OrdersPort) factory.create();
	}
	
	public void processOrder(ProcessOrderRequest order) {	
		
		
		mySoapService.processOrderAsync(order, new AsyncHandler<ProcessOrderResponse>(){

			@Override
			public void handleResponse(Response<ProcessOrderResponse> res) {
				try {
	        		
					BeanFactoryChannelResolver channelResolver = (BeanFactoryChannelResolver) new BeanFactoryChannelResolver(context);

					// Create the Message object
					Message<ProcessOrderResponse> message = MessageBuilder.withPayload(res.get()).build();

					// Send the Message to the handler's input channel
					MessageChannel channel = channelResolver.resolveChannelName("ordersProcessedChannel");
					channel.send(message);

				} catch (Exception e ) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
		
	}
}
