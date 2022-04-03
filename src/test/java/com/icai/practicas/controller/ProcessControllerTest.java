package com.icai.practicas.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @Test
    public void checkValidPostRequestLegacy() throws Exception{
        this.mockMvc
				.perform(
					post("/api/v1/process-step1-legacy")
					.contentType(APPLICATION_FORM_URLENCODED_VALUE)
					.param("fullName", "jorge")
					.param("dni", "54363983X")
					.param("telefono", "618706629"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(ResponseHTMLGenerator.message1)));
    }

	@Test
	public void checkInvalidDNIRequestLegacy() throws Exception{
		this.mockMvc
				.perform(
					post("/api/v1/process-step1-legacy")
					.contentType(APPLICATION_FORM_URLENCODED_VALUE)
					.param("fullName", "jorge")
					.param("dni", "54363983T")
					.param("telefono", "123412341234"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(ResponseHTMLGenerator.message2)));
	}

	@Test
	public void checkInvalidTelefonoRequestLegacy() throws Exception{
		this.mockMvc
				.perform(
					post("/api/v1/process-step1-legacy")
					.contentType(APPLICATION_FORM_URLENCODED_VALUE)
					.param("fullName", "jorge")
					.param("dni", "54363983T")
					.param("telefono", "123412341234"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(ResponseHTMLGenerator.message2)));
	}

	@Test
	public void checkInvalidPostRequestLegacy() throws Exception{
		this.mockMvc
				.perform(
					post("/api/v1/process-step1-legacy")
					.contentType(APPLICATION_FORM_URLENCODED_VALUE)
					.param("dni", "54363983X")
					.param("telefono", "1234567891234"))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof RuntimeException))
				.andExpect(result -> Assert.assertEquals(result.getResponse().getContentAsString(), "{\"message\":\"Contact with the operator\"}"));
	}

	@Test
	public void checkValidPostRequest() throws Exception {
		DataRequest dataRequest = new DataRequest("Jorge", "54363983X", "618706629");
		
		this.mockMvc
				.perform(
					post("/api/v1/process-step1")
					.contentType(APPLICATION_JSON_VALUE)
					.content(asJsonString(dataRequest)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("OK")));
	}

	@Test
	public void checkInvalidPostRequest() throws Exception {
		DataRequest dataRequest = new DataRequest("Jorge");
		
		this.mockMvc
				.perform(
					post("/api/v1/process-step1")
					.contentType(APPLICATION_JSON_VALUE)
					.content(asJsonString(dataRequest)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("KO")));
	}

	@Test
	public void checkInvalidDNIRequest() throws Exception {
		DataRequest dataRequest = new DataRequest("Jorge", "54363983T", "618706629");
		
		this.mockMvc
				.perform(
					post("/api/v1/process-step1")
					.contentType(APPLICATION_JSON_VALUE)
					.content(asJsonString(dataRequest)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("KO")));
	}

	@Test
	public void checkInvalidTelefonoRequest() throws Exception {
		DataRequest dataRequest = new DataRequest("Jorge", "54363983T", "1234567891234");
		
		this.mockMvc
				.perform(
					post("/api/v1/process-step1")
					.contentType(APPLICATION_JSON_VALUE)
					.content(asJsonString(dataRequest)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("KO")));
	}

    public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}