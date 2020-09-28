package com.puianey.crystal.sample.json.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puianey.crystal.common.constant.StringPool;
import com.puianey.crystal.sample.json.sensitive.IdCardSensitive;
import com.puianey.crystal.tools.json.format.BigDecimalFormat;
import com.puianey.crystal.tools.json.format.SensitiveFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: puianey
 * @Date: 2018/8/15 16:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonBean {

	LocalDate localDate;

	@JsonFormat(pattern = "yyyy/MM-dd")
	LocalDate localDate1;

	LocalTime localTime;

	@JsonFormat(pattern = "HH:mm/ss")
	LocalTime localTime1;

	LocalDateTime localDateTime;

	@JsonFormat(pattern = "yyyy-MM/dd HH/mm:ss")
	LocalDateTime localDateTime1;

	Date date;

	@JsonFormat(pattern = "yyyy/MM/dd")
	Date date1;

	Instant instant;

	BigDecimal bigDecimal;

	@BigDecimalFormat
	BigDecimal bigDecimal1;

	@SensitiveFormat(
		start = 0,
		length = 2,
		position = SensitiveFormat.SensitivePositionEnum.START,
		placeholder = StringPool.AMPERSAND
	)
	String string;

	@SensitiveFormat(
		start = 1,
		length = 2,
		position = SensitiveFormat.SensitivePositionEnum.END,
		placeholder = StringPool.AMPERSAND,
		value = IdCardSensitive.class
	)
	String string1;

	String string2;

}
