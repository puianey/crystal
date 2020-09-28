package com.puianey.crystal.sample.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: puianey
 * @Date: 2019-06-25 19:22
 * @Description:
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class SampleWebTestForMockMvc {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGet() throws Exception {

		String msg = "this is a mock test";
		this.mockMvc
			.perform(get("/mvc/get1").param("msg", msg).param("name", "py"))
//			.andDo(print())
			.andExpect(status().isOk());
//			.andReturn();

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
						"  \"localDate\" : \"2019-07-12\",\n" +
						"  \"localDate1\" : \"2019/07-12\",\n" +
						"  \"localTime\" : \"20:55:36\",\n" +
						"  \"localTime1\" : \"20:55/36\",\n" +
						"  \"localDateTime\" : \"2019-07-12 20:55:36\",\n" +
						"  \"localDateTime1\" : \"2019-07/12 20/55:36\",\n" +
						"  \"date\" : 1562936136333,\n" +
						"  \"date1\" : \"2019/07/12\",\n" +
						"  \"instant\" : 1562936136333,\n" +
						"  \"bigDecimal\" : 0,\n" +
						"  \"bigDecimal1\" : \"1001.20\",\n" +
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
