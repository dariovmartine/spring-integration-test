package ar.com.directv.test.spring.integration.nio;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import ar.com.directv.test.spring.integration.types.ProcessOrderRequest;
import ar.com.directv.test.spring.integration.types.ProcessOrderResponse;

public class NIOServiceActivator implements ApplicationContextAware {
	
	String uri;

	private ApplicationContext context;
	
	AsyncRestTemplate asyncRestTemplate;
	

	public void setAsyncRestTemplate(AsyncRestTemplate asyncRestTemplate) {
		this.asyncRestTemplate = asyncRestTemplate;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	class ProcessOrderRequestHttpEntity extends  HttpEntity<ProcessOrderRequest> {
		
		private ProcessOrderRequest order;

		public ProcessOrderRequestHttpEntity(HttpHeaders headers, ProcessOrderRequest order) {
			super(headers);
			this.order = order;
		}

		@Override
		public boolean hasBody() {
			return this.order != null;
		}
		
		@Override
		public ProcessOrderRequest getBody() {
			return this.order;
		}
	}
	
	public void processOrder(ProcessOrderRequest order) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_XML);
		
		
		ListenableFuture<ResponseEntity<ProcessOrderResponse>> response = 
				 asyncRestTemplate.postForEntity(uri, new ProcessOrderRequestHttpEntity(headers,order), ProcessOrderResponse.class);
	    
		response.addCallback(new ListenableFutureCallback<ResponseEntity<ProcessOrderResponse>>() {
			
			@Override
	        public void onSuccess(ResponseEntity<ProcessOrderResponse> result) {
				
				try {
	        		
					BeanFactoryChannelResolver channelResolver = (BeanFactoryChannelResolver) new BeanFactoryChannelResolver(context);

					// Create the Message object
					Message<ProcessOrderResponse> message = MessageBuilder.withPayload(result.getBody()).build();

					// Send the Message to the handler's input channel
					MessageChannel channel = channelResolver.resolveChannelName("ordersProcessedChannel");
					channel.send(message);

				} catch (Exception e ) {
					
					e.printStackTrace();
				}
	        }

	        @Override
	        public void onFailure(Throwable ex) {
	        	"".toString();
	        }
		});
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;		
	}
}
