package ucles.weblab.common.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * A Unit test for the TimeSerializer.
 * 
 * @author Sukhraj
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeSerializerTest {
    
    private TimeSerializer timeSerializer;
    
    JsonGenerator jsonGenerator;
    StringWriter stringWriter;
    
    @Mock
    SerializerProvider provider;
    
    @Before
    public void setUp() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        stringWriter = new StringWriter();
        jsonGenerator = new JsonFactory().createGenerator(stringWriter);
        timeSerializer = new TimeSerializer(formatter);
    }
    
    
    
    @Test
    public void shouldSerializeTimeToHoursAndMinutes() throws Exception {
        
        LocalTime currentLocalTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentLocalTimeParsed = formatter.format(currentLocalTime);
        
        //serialize
        timeSerializer.serialize(currentLocalTime, jsonGenerator, provider);        
        jsonGenerator.flush();                
        String time1Serialized = stringWriter.toString(); 
        
        assertNotNull(time1Serialized);
        assertTrue(time1Serialized.length() > 0);
        assertTrue(time1Serialized.contains(currentLocalTimeParsed));
        stringWriter.close();
    }
}
