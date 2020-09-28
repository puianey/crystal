package com.puianey.crystal.sample.web.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puianey.crystal.tools.json.format.BigDecimalFormat;
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
 * @Date: 2019-07-12 19:34
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
public class WebBean {

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

    String string2;

}
