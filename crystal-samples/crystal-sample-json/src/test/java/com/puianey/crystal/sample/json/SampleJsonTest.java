package com.puianey.crystal.sample.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puianey.crystal.sample.json.bean.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: puianey
 * @Date: 2019-06-25 19:22
 * @Description:
 */
@SpringBootTest
@Slf4j
public class SampleJsonTest {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void serializer() throws JsonProcessingException {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		var jsonBean = JsonBean.builder().localDate(LocalDate.now())
			.localDate1(LocalDate.now())
			.localTime(LocalTime.now())
			.localTime1(LocalTime.now())
			.localDateTime(LocalDateTime.now())
			.localDateTime1(LocalDateTime.now())
			.date(new Date())
			.date1(new Date())
			.instant(Instant.now())
			.bigDecimal(BigDecimal.ZERO)
			.bigDecimal1(BigDecimal.valueOf(1001.1993456))
			.string("abcdefg")
			.string1("abcdefg")
			.string2("测试2")
			.build();
		var json = objectMapper.writeValueAsString(jsonBean);
		stopWatch.stop();
		log.info("响应时间:{}-纳秒", stopWatch.getTotalTimeMillis());
		log.info("{}", json);

		assertThat(json).isNotNull();

	}

	@Test
	public void deserializer() throws IOException {

		var jsonBean = objectMapper.readValue("{\n" +
			"  \"localDate\" : \"2019-06-28\",\n" +
			"  \"localDate1\" : \"2019/06-28\",\n" +
			"  \"localTime\" : \"14:54:05\",\n" +
			"  \"localTime1\" : \"14:54/05\",\n" +
			"  \"localDateTime\" : \"2019-06-28 14:54:05\",\n" +
			"  \"localDateTime1\" : \"2019-06/28 14/54:05\",\n" +
			"  \"date\" : 1561704845649,\n" +
			"  \"date1\" : \"2019/06/28\",\n" +
			"  \"instant\" : 1561704845649,\n" +
			"  \"bigDecimal\" : 0,\n" +
			"  \"bigDecimal1\" : \"1001.20102323445\"\n" +
			"}", JsonBean.class);

		log.info("{}", jsonBean);

		var json = objectMapper.writeValueAsString(jsonBean);
		log.info("{}", json);

		assertThat(jsonBean.getInstant()).isEqualTo(Instant.ofEpochMilli(1561704845649L));


	}

}
