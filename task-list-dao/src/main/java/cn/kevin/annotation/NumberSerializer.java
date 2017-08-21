package cn.kevin.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.text.DecimalFormat;

/**
 * 数字序列化
 * Created by yongkang.zhang on 2017/8/21.
 */
public class NumberSerializer extends JsonSerializer<Number> implements ContextualSerializer {

    private final String format;

    public NumberSerializer(String format) {
        this.format = format;
    }

    public NumberSerializer() {
        this.format = null;
    }

    @Override
    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        assert this.format != null;
        DecimalFormat format = new DecimalFormat(this.format);
        jsonGenerator.writeString(format.format(number));
    }

    @Override
    public JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // final AnnotatedElement annotated = beanProperty.getMember().getAnnotated(); 这个是用在get方法上注解上的
        return new NumberSerializer(beanProperty.getAnnotation(JsonNumberFormat.class).format());
    }
}
