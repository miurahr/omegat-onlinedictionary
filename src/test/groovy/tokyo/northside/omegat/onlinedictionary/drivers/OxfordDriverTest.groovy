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
        def entries = driver.readEntries("software")
        assert(entries.size() > 0)
        assert(entries.contains("the programs and other operating information used by a computer"))
    }
}
