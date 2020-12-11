package tokyo.northside.omegat.onlinedictionary

import org.omegat.util.Language

import org.junit.Test

import static org.junit.Assert.*


class OnlineDictionaryMainTest {

    def resource = OnlineDictionaryPlugin.OnlineDictionaryMain.class.getClassLoader().getResource("oxford_service.yml")
    def appId = System.getenv("OXFORDID")
    def appKey = System.getenv("OXFORDKEY")

    @Test
    void onlineDictionaryGetServices() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        def service = onlineDictionary.getService(new File(resource.toURI()))
        assertNotNull(service)
    }

    @Test
    void isSupportedFile() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        assertTrue(new File(resource.toURI()).isFile())
        assertTrue(new File(resource.toURI()).toPath().toString().endsWith("service.yml"))
        def service = onlineDictionary.getService(new File(resource.toURI()))
        assertEquals("oxfordapi", service.getDriver())
        assertTrue(onlineDictionary.isSupportedFile(new File(resource.toURI())))
    }

    @Test
    void onlineDictionaryLoadDict() {
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain(new Language("en"), new Language("es"))
        def dictionary = onlineDictionary.loadDict(new File(resource.toURI()))
        assertNotNull(dictionary)
    }

    @Test
    void onlineDictionarySearch() {
        def source = new Language("en")
        def target = new Language("it")
        def onlineDictionary = new OnlineDictionaryPlugin.OnlineDictionaryMain()
        def service = onlineDictionary.getService(new File(resource.toURI()))
        service.setKey(appId)
        service.setSecret(appKey)
        def dictionary = new OnlineDictionaryPlugin.OnlineDictionary(service, source, target);
        def definitions = dictionary.readArticles("translation")
        def entry = definitions.get(0)
        assertEquals("translation", entry.getWord())
        assertEquals("the process of translating words or text from one language into another/the process of moving something from one place to another//traduzione(Oxford)", entry.getArticle())
    }
}
