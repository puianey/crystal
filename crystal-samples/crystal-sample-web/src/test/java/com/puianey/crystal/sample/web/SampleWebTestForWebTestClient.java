package com.puianey.crystal.sample.web;

import com.puianey.crystal.sample.web.bean.WebBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: puianey
 * @Date: 2019-06-25 19:22
 * @Description:
 */
@SpringBootTest("spring.main.web-application-type=reactive")//也可以在application.yml中配置
@AutoConfigureWebTestClient
@Slf4j
public class SampleWebTestForWebTestClient {

	@Autowired
	private WebTestClient webTestClient;


	@Test
	public void testGet() {

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
	public void testPost(){
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
	public void testPost2(){
		this.webTestClient
			.post()
			.uri("/flux/post2")
			.exchange()
			.expectStatus().isOk()
			.expectBody(WebBean.class)
			.isEqualTo(WebBean.builder().localDate(LocalDate.now()).string2("测试2").build());
	}

}
