package com.seleniumexpress.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	public TestController() {
		System.out.println("Inside TestController");
	}

	@GetMapping("/show")
	public String showHello()
	{
		return "Inside Show";
	}
	
	@GetMapping("/hii")
	public String showHii()
	{
		return "Inside Hiii";
	}
	
	@GetMapping("/bye")
	public String showTestBye()
	{
		return "Inside Byeee";
	}
}
