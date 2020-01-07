/**
 * 
 */
package io.arun.camel.camelrabbitmqserver.config;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import io.arun.camel.camelrabbitmqserver.service.BotService;

/**
 * @author arun
 *
 */
@Component
public class RabbitConfig extends RouteBuilder{
	
//	JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(String.class);
	
	@Override
	public void configure() throws Exception {
//		from("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=client-to-server.queue&autoDelete=false&routingKey=client-server-key").id("rabbitRoute")
//			.process(new Processor() {	
//				@Override
//				public void process(Exchange exchange) throws Exception {
//					String msg = exchange.getIn().getBody().toString();
//					System.out.println("Client:"+msg);
//					String reply = BotService.msgProducer(msg);
//					System.out.println("Server:"+reply);
//					exchange.getIn().setBody(reply);
//				}
//			})
//			.to("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=server-to-client.queue&autoDelete=false&routingKey=client-server-key").end();
		
		from("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=client-to-server.queue&autoDelete=false&routingKey=client-server-key")
			.to("bean:io.arun.camel.camelrabbitmqserver.service.BotService?method=msgProducer");
	
		from("direct:rabbitMQServer")
			.to("rabbitmq://localhost:5672/MSG-QUEUE.exchange?queue=server-to-client.queue&autoDelete=false&routingKey=server-client-key");
	
	}

}
