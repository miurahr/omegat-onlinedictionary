package tokyo.northside.omegat.onlinedictionary

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

import org.junit.Test
import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService

import static org.junit.Assert.*


class OnlineDictionaryServiceYamlTest {

    def resource = OnlineDictionaryPlugin.OnlineDictionaryMain.class.getClassLoader().getResource("service.yml")

    @Test
    void readYamlTest() throws Exception {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        def service = om.readValue(new File(resource.toURI()), OnlineDictionaryService.class);
        assertEquals("omegawiki", service.getName())
        assertEquals("omegawiki", service.getDriver())
        assertEquals("http://omegawiki.org/api.php", service.getEndpointUrl())
        assertEquals("abc", service.getKey())
        assertEquals("123", service.getSecret())
    }
}
