package tokyo.northside.omegat.onlinedictionary.drivers

import org.omegat.util.Language
import org.junit.Test
import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService
import tokyo.northside.omegat.utils.QueryUtil

import static org.junit.Assert.*

class OmegawikiDriverTest {
    def endpointUrl = "http://omegawiki.org/api.php"

    @Test
    void constructorTest() {
        def service = new OnlineDictionaryService("OmegaWiki", endpointUrl, "omegawiki")
        def driver = new OmegawikiDriver(service, new Language("en"), new Language("es"))
        assertNotNull(driver)
    }

    @Test
    void getNameTest() {
        def service = new OnlineDictionaryService("OmegaWiki", endpointUrl, "omegawiki")
        def driver = new OmegawikiDriver(service, new Language("en"), new Language("es"))
        assertEquals("OmegaWiki", driver.getName())
    }

    @Test
    void getQueryTest() {
        def word = "translate"
        def queryUrl = endpointUrl.concat("?action=ow_express&format=json&search=").concat(word);
        def result = QueryUtil.query(queryUrl, new HashMap<String, Object>())
        assertNotNull(result)
    }

    @Test
    void getQueryExpression() {
        def service = new OnlineDictionaryService("OmegaWiki", endpointUrl, "omegawiki")
        def driver = new OmegawikiDriver(service, new Language("en"), new Language("es"))
        def expressions = driver.queryExpression("translate")
        assertEquals("English", expressions.get(0).lang)
    }

    @Test
    void getDefinition() {
        def service = new OnlineDictionaryService("OmegaWiki", endpointUrl, "omegawiki")
        def driver = new OmegawikiDriver(service, new Language("en"), new Language("es"))
        def definitions = driver.readEntries("translate")
        assertNotNull(definitions)
    }
}
