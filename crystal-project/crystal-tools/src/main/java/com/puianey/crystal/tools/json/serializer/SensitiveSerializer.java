package com.puianey.crystal.tools.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.puianey.crystal.common.util.$;
import com.puianey.crystal.common.util.Try;
import com.puianey.crystal.tools.base.holder.SpringContextHolder;
import com.puianey.crystal.tools.json.Sensitive;
import com.puianey.crystal.tools.json.format.SensitiveFormat;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: puianey
 * @Date: 2019-08-08 18:38
 * @Description:
 */
public class SensitiveSerializer extends StdSerializer<String> implements ContextualSerializer {

    private SensitiveFormat sensitiveFormat;

    public SensitiveSerializer() {
        super(String.class);
    }

    private SensitiveSerializer(SensitiveFormat sensitiveFormat) {
        super(String.class);
        this.sensitiveFormat = sensitiveFormat;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {

        if (Objects.isNull(sensitiveFormat)) {
            jsonGenerator.writeString(value);
            return;
        }
        if (!sensitiveFormat.value().isInterface()) {
            var sensitive = SpringContextHolder.getBeanForMultiImplClass(Sensitive.class, sensitiveFormat.value());
            jsonGenerator.writeString(sensitive.sensitive(value));
            return;
        }
        if (sensitiveFormat.length() <= 0) {
            jsonGenerator.writeString(value);
            return;
        }

        Optional.of(sensitiveFormat)
                .filter(format -> Objects.equals(format.position(), SensitiveFormat.SensitivePositionEnum.START))
                .ifPresentOrElse(
                        Try.of(format -> jsonGenerator.writeString(replace(value, sensitiveFormat, false))),
                        Try.of(() -> jsonGenerator.writeString(replace(value, sensitiveFormat, true)))
                );

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        return Optional.ofNullable(property.getAnnotation(SensitiveFormat.class))
                .map(SensitiveSerializer::new)
                .orElseGet(SensitiveSerializer::new);
    }

    private String replace(String value, SensitiveFormat sensitiveFormat, boolean isFromBack) {
        if (isFromBack) {
            return $.replaceFromBack(value, sensitiveFormat.start(), sensitiveFormat.length(), sensitiveFormat.placeholder());
        }
        return $.replace(value, sensitiveFormat.start(), sensitiveFormat.length(), sensitiveFormat.placeholder());
    }
}
