package tokyo.northside.omegat

import org.omegat.util.Language

import org.junit.Test
import static org.junit.Assert.*


class OnlineDictionaryMainTest {

    def resource = OnlineDictionaryPlugin.OnlineDictionaryMain.class.getClassLoader().getResource("service.yml")

    @Test
    void constructorTest() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        assertTrue(onlineDictionary.isSupportedFile(new File(resource.toURI())))
    }

    @Test
    void onlineDictionaryGetServices() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        def services = onlineDictionary.getService(new File(resource.toURI()))
        assertNotNull(services)
    }

    @Test
    void onlineDictionaryLoadDict() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        def dictionary = onlineDictionary.loadDict(new File(resource.toURI()))
        assertNotNull(dictionary)
    }

    @Test
    void onlineDictionarySearch() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("it"))
        def dictionary = onlineDictionary.loadDict(new File(resource.toURI()))
        def definitions = dictionary.readArticles("translation")
        def entry = definitions.get(2)
        assertEquals("translation", entry.getWord())
        assertEquals("Move of an object to a new location without change in the size or orientation of an object.", entry.getArticle())
    }
}
