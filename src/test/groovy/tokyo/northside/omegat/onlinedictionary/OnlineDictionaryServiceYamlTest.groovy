package tokyo.northside.omegat.onlinedictionary

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

import org.junit.Test
import tokyo.northside.omegat.onlinedictionary.OnlineDictionaryPlugin
import tokyo.northside.omegat.onlinedictionary.OnlineDictionaryService

import static org.junit.Assert.*


class OnlineDictionaryServiceYamlTest {

    def resource = OnlineDictionaryPlugin.OnlineDictionaryMain.class.getClassLoader().getResource("service.yml")

    @Test
    void readYamlTest() throws Exception {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        def services = om.readValue(new File(resource.toURI()), OnlineDictionaryService.class);
        assertEquals(services.getName(), "omegawiki")
        assertEquals(services.getDriver(), "omegawiki")
        assertEquals(services.getEndpointUrl(), "http://omegawiki.org/api.php")
    }
}
