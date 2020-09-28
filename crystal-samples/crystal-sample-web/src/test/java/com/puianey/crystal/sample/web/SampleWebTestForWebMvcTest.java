package com.puianey.crystal.sample.web;

import com.puianey.crystal.sample.web.controller.WebMvcController;
import com.puianey.crystal.sample.web.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: puianey
 * @Date: 2019-06-25 19:22
 * @Description:
 */
@WebMvcTest(WebMvcController.class)
@Slf4j
public class SampleWebTestForWebMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WebService webService;

	@BeforeEach
	public void before() {
		given(this.webService.test(1L))
			.willReturn("1111");
	}

	@Test
	public void testGet() throws Exception {

		given(this.webService.test(1L))
			.willReturn("2222");

		String msg = "this is a mock test";
		this.mockMvc
			.perform(get("/mvc/get1").param("msg", msg).param("name", "py"))
			.andExpect(status().isOk());

	}

	@Test
	public void testGet2() throws Exception {

		String msg = "this is a mock test";
		this.mockMvc
			.perform(get("/mvc/get2").param("key", "value"))
			.andExpect(content().string("测试"));

	}

	@Test
	public void testPost() throws Exception {

		this.mockMvc
			.perform(
				post("/mvc/post1")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\n" +
						"  \"instant\" : 1562936136333,\n" +
						"  \"string2\" : \"测试2aa\"\n" +
						"}")
			)
			.andExpect(status().isOk());

	}

	@Test
	public void testPost2() throws Exception {

		this.mockMvc
			.perform(post("/mvc/post2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(content()
				.json("{\n" +
					"  \"string2\" : \"测试2\"\n" +
					"}"
				));

	}

	@Test
	public void testPost2(@Autowired MockMvc mvc) throws Exception {

		mvc
			.perform(post("/mvc/post2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(content()
				.json("{\n" +
					"  \"string2\" : \"测试2\"\n" +
					"}"
				));

	}

}
