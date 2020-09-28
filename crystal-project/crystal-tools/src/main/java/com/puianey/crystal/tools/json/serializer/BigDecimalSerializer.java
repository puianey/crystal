package com.puianey.crystal.tools.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.puianey.crystal.tools.json.format.BigDecimalFormat;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: puianey
 * @Date: 2019-08-08 18:36
 * @Description:
 */
public class BigDecimalSerializer extends StdSerializer<BigDecimal> implements ContextualSerializer {

    private BigDecimalFormat bigDecimalFormat;

    public BigDecimalSerializer() {
        super(BigDecimal.class);
    }

    public BigDecimalSerializer(BigDecimalFormat bigDecimalFormat) {
        super(BigDecimal.class);
        this.bigDecimalFormat = bigDecimalFormat;
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {

        if (Objects.isNull(bigDecimalFormat)) {
            jsonGenerator.writeNumber(value);
            return;
        }
        jsonGenerator.writeString(new DecimalFormat(bigDecimalFormat.value()).format(value));

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        return Optional.ofNullable(property.getAnnotation(BigDecimalFormat.class))
                .map(BigDecimalSerializer::new)
                .orElseGet(BigDecimalSerializer::new);
    }

}
