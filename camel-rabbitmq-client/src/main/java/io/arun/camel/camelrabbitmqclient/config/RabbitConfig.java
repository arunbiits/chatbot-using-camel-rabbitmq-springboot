/**
 * 
 */
package io.arun.camel.camelrabbitmqclient.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author arun
 *
 */
@Component
public class RabbitConfig extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:rabbitMQClient")
			.to("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=client-to-server.queue&autoDelete=false&routingKey=client-server-key");
		
		from("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=server-to-client.queue&autoDelete=false&routingKey=server-client-key")
			.to("bean:io.arun.camel.camelrabbitmqclient.service.MyService?method=serverReply");
	}
	
}
