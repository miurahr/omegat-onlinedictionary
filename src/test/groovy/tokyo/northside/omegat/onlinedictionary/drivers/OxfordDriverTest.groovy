package tokyo.northside.omegat.onlinedictionary.drivers

import org.junit.jupiter.api.Test
import org.omegat.util.Language


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
}
