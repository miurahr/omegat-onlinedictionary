package tokyo.northside.omegat

import org.omegat.util.Language

import org.junit.Test
import static org.junit.Assert.*


class OnlineDictionaryTest {

    def resource = OnlineDictionary.class.getClassLoader().getResource("service.yml")

    @Test
    void constructorTest() {
        def onlineDictionary = new OnlineDictionary(new Language("en"), new Language("es"))
        assertTrue(onlineDictionary.isSupportedFile(new File(resource.toURI())))
    }

    @Test
    void onlineDictionaryGetServices() {
        def onlineDictionary = new OnlineDictionary(new Language("en"), new Language("es"))
        def services = onlineDictionary.getService(new File(resource.toURI()))
        assertNotNull(services)
    }

    @Test
    void onlineDictionaryLoadDict() {
        def onlineDictionary = new OnlineDictionary(new Language("en"), new Language("es"))
        def dictionary = onlineDictionary.loadDict(new File(resource.toURI()))
        assertNotNull(dictionary)
    }

    @Test
    void onlineDictionarySearch() {
        def onlineDictionary = new OnlineDictionary(new Language("en"), new Language("es"))
        def dictionary = onlineDictionary.loadDict(new File(resource.toURI()))
        def definitions = dictionary.readArticles("translate")
        def entry = definitions.get(0)
        assertEquals("translate", entry.getWord())
        assertTrue(entry.getArticle().startsWith("To change a written or spoken text from one language to another."))
    }
}
