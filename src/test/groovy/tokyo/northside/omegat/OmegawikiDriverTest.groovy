package tokyo.northside.omegat

import org.omegat.util.Language
import org.junit.Test
import static org.junit.Assert.*

class OmegawikiDriverTest {
    def endpointUrl = "http://omegawiki.org/api.php"

    @Test
    void constructorTest() {
        def driver = new OmegawikiDriver(endpointUrl, new Language("en"), new Language("es"))
        assertNotNull(driver)
    }

    @Test
    void getNameTest() {
        def driver = new OmegawikiDriver(endpointUrl, new Language("en"), new Language("es"))
        assertEquals("OmegaWiki", driver.getName())
    }

    @Test
    void getQueryTest() {
        def driver = new OmegawikiDriver(endpointUrl, new Language("en"), new Language("es"))
        def word = "translate"
        def queryUrl = endpointUrl.concat("?action=ow_express&format=json&search=").concat(word);
        def result = driver.query(queryUrl)
        assertNotNull(result)
    }

    @Test
    void getQueryExpression() {
        def driver = new OmegawikiDriver(endpointUrl, new Language("en"), new Language("es"))
        def expressions = driver.queryExpression("translate")
        assertEquals("English", expressions.get(0).lang)
    }

    @Test
    void getDefinition() {
        def driver = new OmegawikiDriver(endpointUrl, new Language("en"), new Language("es"))
        def definitions = driver.readDefinition("translate")
        assertNotNull(definitions)
    }
}
