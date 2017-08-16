
package hello;

import java.io.IOException;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJms
public class Application {
	
	static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Bean
	public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
		
		BrokerService broker = new BrokerService();
        String url = "tcp://localhost:61616?wireFormat.maxInactivityDuration=0";
		
        try {
			
			broker.addConnector(url);
			broker.setPersistent(false);
			broker.setUseShutdownHook(false);
			broker.setUseJmx(false);
			broker.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
    
		return new ActiveMQConnectionFactory(url);
	}

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.print("ENTER para enviar mensajes:");
        System.in.read();
        for (int i=0; i < 300; i++) {
        	
        	jmsTemplate.convertAndSend("processOrderQueque", Order.getOrder());
        	logger.info("Enviando mensaje a la cola");
        }
        System.out.print("ENTER para terminar");
        System.in.read();
    }

}
