import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ebastic on 11/11/2016.
 */
public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {

        }
        return paramDeserializationContext.parseDate(str);
    }
}



import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ConfigImplTest {
    @Test
    public void shouldNotFail_whenLoadCorrectFile() {
        Config config = new ConfigImpl("test.properties");

        try {
            config.load();
        } catch (Exception e) {
            fail("Should not fail");
        }
    }

    @Test(expected = Exception.class)
    public void shouldFail_whenLoadWrongFile() throws Exception {
        Config config = new ConfigImpl("file-does-not-exists.properties");
        config.load();
    }

    @Test
    public void shouldGetCorrectValue_whenLoadFile() {
        Config config = new ConfigImpl("test.properties");
        try {
            config.load();
        } catch (Exception e) {
            fail("Should not fail");
        }

        assertTrue(config.getProperty("test.key").equals("value"));
    }
}

in test/resource/test.properties
test.key=value

public class UnmarshallTest {

//    @Test
//    public void unmarshal() {
//        try (InputStream path = this.getClass().getClassLoader().getResourceAsStream("good.xml")) {
//            Files.copy(InputStream path, Path target);
//        } catch (IOException e) {
//            throw e;
//        }
//    }
} sa iei din jar
