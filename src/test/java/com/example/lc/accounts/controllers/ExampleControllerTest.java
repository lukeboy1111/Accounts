package com.example.lc.accounts.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.lc.accounts.controller.ExampleController;


@RunWith(SpringRunner.class)
@WebMvcTest(ExampleController.class)
public class ExampleControllerTest {

	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testingHome() throws Exception {
		 	this.mvc.perform(get("/").accept(MediaType.TEXT_HTML))
	        .andExpect(status().is(200));
	}
	
	@Test
	public void testingExample() throws Exception {
		 	this.mvc.perform(get("/example").accept(MediaType.TEXT_HTML))
	        .andExpect(status().is(200));
	}
}
