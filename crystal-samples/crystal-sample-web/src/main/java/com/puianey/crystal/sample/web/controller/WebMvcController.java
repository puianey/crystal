package com.puianey.crystal.sample.web.controller;

import com.puianey.crystal.sample.web.bean.WebBean;
import com.puianey.crystal.sample.web.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @Author: puianey
 * @Date: 2019-07-12 19:33
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("mvc")
public class WebMvcController {

	@Autowired
	WebService webService;

	@GetMapping("get1")
	public WebBean get(String msg, String name) {

		log.info("webService调用结果: {}", webService.test(1L));

		log.info("参数为：{} & {}", msg, name);

		return getWebBean();

	}

	@GetMapping("get2")
	public String get() {

		log.info("webService调用结果: {}", webService.test(1L));

		return "测试";

	}

	@PostMapping("post1")
	public void post(@RequestBody WebBean webBean) {

		log.info("参数为；{}", webBean);

	}

	@PostMapping("post2")
	public WebBean post() {

		return getWebBean();

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
