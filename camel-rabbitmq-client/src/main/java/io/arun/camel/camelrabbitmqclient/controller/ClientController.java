/**
 * 
 */
package io.arun.camel.camelrabbitmqclient.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.arun.camel.camelrabbitmqclient.service.MyService;



/**
 * @author arun
 *
 */
@Controller
public class ClientController {
	
	@Produce(uri = "direct:rabbitMQClient")
	ProducerTemplate producer;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("RES_MSG", MyService.REPLY_MSG);
		return "index";
	}
	
	@GetMapping("/send")
	public String sendToServer(@RequestParam("req_msg") String msg, Model model) throws Exception {
		producer.sendBody(producer.getDefaultEndpoint(), msg);
		Thread.sleep(1000);
		String reply = null;
		if(MyService.REPLY_MSG.equals(msg)) {
			reply = "Sorry! Server is not up and running";
		}else {
			reply = MyService.REPLY_MSG == null ? "Sorry! Server is not up and running!": MyService.REPLY_MSG;
		}
		model.addAttribute("REQ_MSG", msg);
		model.addAttribute("RES_MSG", reply);
		return "index";
	}
	
}
