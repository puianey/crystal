package com.puianey.crystal.sample.json.sensitive;

import com.puianey.crystal.tools.json.Sensitive;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: puianey
 * @Date: 2019-07-04 18:48
 * @Description:
 */
@Component
public class IdCardSensitive implements Sensitive {

    @Override
    public String sensitive(String oldStr) {
        return Optional.ofNullable(oldStr).map(str -> str.concat("--idCard")).orElse(null);
    }
}
