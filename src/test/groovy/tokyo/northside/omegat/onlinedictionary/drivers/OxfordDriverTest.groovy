package tokyo.northside.omegat.onlinedictionary.drivers

import org.omegat.util.Language
import org.junit.Test

import static org.junit.Assert.*


class OxfordDriverTest {

    def endpointUrl = "https://od-api.oxforddictionaries.com/api/v2/"
    def appId = System.getenv("OXFORDID")
    def appKey = System.getenv("OXFORDKEY")

    @Test
    void constructorTest() {
        OxfordDriver driver = new OxfordDriver(endpointUrl, appId, appKey, new Language("en"), new Language("es"))
        def requestUrl = driver.getEntriesRequestUrl("ace", false);
        def results = driver.query(requestUrl, "ace")
        assert(results.size() > 0)
        assertEquals("ace", results.get(0).getWord())
    }

    @Test
    void readDefinitionTest() {
        OxfordDriver driver = new OxfordDriver(endpointUrl, appId, appKey, new Language("en"), new Language("ja"))
        def definitions = driver.readDefinition("software")
        assert(definitions.size() > 0)
        def text = definitions.get(0)
        assertEquals("the programs and other operating information used by a computer", text)
        text = driver.readDefinition("ace").get(0)
        assertEquals("a playing card with a single spot on it, ranked as the highest card in its suit in most card games", text)
    }
}
