package ucles.weblab.common.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A custom time serializer that will serialize a time using the hours and minutes only.
 *
 * @author Sukhraj
 */
public class TimeSerializer extends LocalTimeSerializer {

    private static final String TIME_PATTERN = "HH:mm";

    public TimeSerializer() {
        super(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    /**
     * A method that will convert a Java time object to a JSON string only showing
     * the hours and minutes.
     */
    @Override
    public void serialize(LocalTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
        if (useTimestamp(provider)) {
            g.writeStartArray();
            g.writeNumber(value.getHour());
            g.writeNumber(value.getMinute());
            g.writeEndArray();
        } else {
            DateTimeFormatter dtf = _formatter;
            if (dtf == null) {
                dtf = _defaultFormatter();
            }
            g.writeString(value.format(dtf));
        }
    }

    /**
     * Override this method to return the format which just have hours and minutes.
     */
    @Override
    protected LocalTimeSerializer withFormat(Boolean useTimestamp, DateTimeFormatter dtf, JsonFormat.Shape shape) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return new LocalTimeSerializer(formatter);
    }

}
