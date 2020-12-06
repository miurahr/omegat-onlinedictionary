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
        def results = driver.queryEntries("ace")
        assertEquals("ace", results.get(0).getWord())
    }

    @Test
    void readDefinitionTest() {
        OxfordDriver driver = new OxfordDriver(endpointUrl, appId, appKey, new Language("en"), new Language("ja"))
        def text = driver.readDefinition("ace").get(0)
        assertEquals("a playing card with a single spot on it, ranked as the highest card in its suit in most card games", text)
    }
}
