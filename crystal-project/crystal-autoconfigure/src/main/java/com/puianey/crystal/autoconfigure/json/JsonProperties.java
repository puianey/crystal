package com.puianey.crystal.autoconfigure.json;

import com.puianey.crystal.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: puianey
 * @Date: 2018/8/29 12:09
 */
@Data
@ConfigurationProperties(prefix = JsonProperties.JSON_PREFIX)
//@Validated  可以校验数据
public class JsonProperties {

	public static final String JSON_PREFIX = "framework.json";

	final DateTime dateTime = new DateTime();

	final Sensitive sensitive = new Sensitive();

	@Data
	static class DateTime {

		String localDateFormat = Constants.LOCAL_DATE_FORMAT;

		String localTimeFormat = Constants.LOCAL_TIME_FORMAT;

		String localDateTimeFormat = Constants.LOCAL_DATE_TIME_FORMAT;

	}

	@Data
	static class Sensitive {
		Boolean enable = false;
	}

}
