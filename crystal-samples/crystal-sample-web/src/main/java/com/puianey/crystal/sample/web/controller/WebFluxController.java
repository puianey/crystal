package com.puianey.crystal.sample.web.controller;

import com.puianey.crystal.sample.web.bean.WebBean;
import com.puianey.crystal.sample.web.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: puianey
 * @Date: 2019-07-12 19:33
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("flux")
public class WebFluxController {

	@Autowired
	WebService webService;

	@GetMapping("get1")
	public Mono<WebBean> get(String msg, String name) {

		log.info("webService调用结果: {}", webService.test(1L));

		log.info("参数为：{} & {}", msg, name);

		return Mono.just(getWebBean());

	}

	@GetMapping("get2")
	public Mono<String> get() {

		log.info("webService调用结果: {}", webService.test(1L));

		return Mono.just("测试");

	}

	@GetMapping("get3/{name}")
	public Flux<String> get(@PathVariable String name) {

		log.info("参数name: {}", name);

		return Flux.fromIterable(List.of("测试"));

	}

	@PostMapping("post1")
	public void post(@RequestBody WebBean webBean) {

		log.info("参数为；{}", webBean);

	}

	@PostMapping("post2")
	public Mono<WebBean> post() {

		return Mono.just(getWebBean());

	}

	private WebBean getWebBean() {
		return WebBean.builder().localDate(LocalDate.now())
//			.localDate1(LocalDate.now())
//			.localTime(LocalTime.now())
//			.localTime1(LocalTime.now())
//			.localDateTime(LocalDateTime.now())
//			.localDateTime1(LocalDateTime.now())
//			.date(new Date())
//			.date1(new Date())
//			.instant(Instant.now())
//			.bigDecimal(BigDecimal.ZERO)
//			.bigDecimal1(BigDecimal.valueOf(1001.1993456))
			.string2("测试2")
			.build();
	}
}
