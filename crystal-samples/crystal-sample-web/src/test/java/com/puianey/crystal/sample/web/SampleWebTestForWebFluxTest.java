package com.puianey.crystal.sample.web;

import com.puianey.crystal.sample.web.bean.WebBean;
import com.puianey.crystal.sample.web.controller.WebFluxController;
import com.puianey.crystal.sample.web.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 * @Author: puianey
 * @Date: 2019-06-25 19:22
 * @Description:
 */
@WebFluxTest(WebFluxController.class)
@Slf4j
public class SampleWebTestForWebFluxTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private WebService webService;

	@BeforeEach
	public void before(){
		given(this.webService.test(1L))
			.willReturn("前置打桩");
	}

	@Test
	public void testGet() {

		given(this.webService.test(1L))
			.willReturn("方法内部打桩");

		this.webTestClient
			.get()
			.uri("/flux/get1?msg={msg}&name={name}", "msg", "朋友")
			.exchange()
			.expectStatus().isOk()
			.expectBody(WebBean.class);

	}

	@Test
	public void testGet2() {
		this.webTestClient
			.get()
			.uri("/flux/get2")
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.isEqualTo("测试");
	}

	@Test
	public void testGet3() {

		this.webTestClient
			.get()
			.uri("/flux/get3/{name}", "朋友")
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(String.class)
			.isEqualTo(List.of("测试"));

	}

	@Test
	public void testPost() {
		this.webTestClient
			.post()
			.uri("/flux/post1")
//			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(WebBean.builder().localDate(LocalDate.now()).string2("测试2").build()), WebBean.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody().isEmpty();
	}

	@Test
	public void testPost2() {
		this.webTestClient
			.post()
			.uri("/flux/post2")
			.exchange()
			.expectStatus().isOk()
			.expectBody(WebBean.class)
			.isEqualTo(WebBean.builder().localDate(LocalDate.now()).string2("测试2").build());
	}

}
