package ar.com.directv.test.spring.integration.nio;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

import ar.com.directv.test.spring.integration.types.OrdersPort;
import ar.com.directv.test.spring.integration.types.ProcessOrderRequest;
import ar.com.directv.test.spring.integration.types.ProcessOrderResponse;

public class NIOServiceActivator   {
	
	OrdersPort mySoapService;
	
	PollableChannel outputChannel;
	
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
					Message<ProcessOrderResponse> message = MessageBuilder.withPayload(res.get()).build();

					outputChannel.send(message);

				} catch (Exception e ) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	public PollableChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(PollableChannel outputChannel) {
		this.outputChannel = outputChannel;
	}

	
}
