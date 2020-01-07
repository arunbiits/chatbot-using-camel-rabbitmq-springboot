package io.arun.camel.camelrabbitmqserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.arun.camel.camelrabbitmqserver.service.BotService;

@RestController
public class BotController {
	
	@Autowired
	BotService botService;

	@GetMapping("/test")
	public String testBot(@RequestParam("msg") String msg) {
		return null;
	}
	
}
