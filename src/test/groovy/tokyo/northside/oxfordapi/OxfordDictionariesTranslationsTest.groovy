package tokyo.northside.oxfordapi;

import org.apache.commons.io.IOUtils
import org.junit.Test

import static org.junit.Assert.*


class OxfordDictionariesTranslationsTest {

    @Test
    void testTranslationsParse1() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/translation_result1.json")
        def json = IOUtils.toString(resource, "UTF-8")
        def parser = new OxfordDictionaryParser("documentation")
        parser.parse(json)
        def results = parser.getResults()
        def result = results.get(0)
        assertEquals("documentation", result.getId())
        def lexicalEntries = result.getLexicalEntries()
        assertEquals("en", lexicalEntries.get(0).getLanguage())
        assertEquals("documentation", lexicalEntries.get(0).getText())
        def entries = lexicalEntries.get(0).getEntries()
        assert(entries.size() == 1)
        def senses = entries.get(0).getSenses()
        assert(senses.size() == 2)
        assertEquals("b-en-de0010238.002", senses.get(0).getId())
        def translations = senses.get(0).getTranslations()
        assert(translations.size() == 1)
        assertEquals("Dokumentation", translations.get(0).getText())
    }

    @Test
    void testTranslationsParse2() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/translation_result2.json")
        def json = IOUtils.toString(resource, "UTF-8")
        def parser = new OxfordDictionaryParser("typographic")
        parser.parse(json)
        def results = parser.getResults()
        def result = results.get(0)
        assertEquals("typographic", result.getId())
        def lexicalEntries = result.getLexicalEntries()
        def entries = lexicalEntries.get(0).getEntries()
        def senses = entries.get(0).getSenses()
        def translations = senses.get(0).getTranslations()
        assert(translations.size() == 1)
        assertEquals("typographisch", translations.get(0).getText())
    }

    @Test
    void testTranslationsParse3() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/translation_result3.json")
        def json = IOUtils.toString(resource, "UTF-8")
        def parser = new OxfordDictionaryParser("access")
        parser.parse(json)
        def results = parser.getResults()
        def result = results.get(0)
        assertEquals("access", result.getId())
        def lexicalEntries = result.getLexicalEntries()
        def entries = lexicalEntries.get(0).getEntries()
        def senses = entries.get(0).getSenses()
        def translations = senses.get(0).getTranslations()
        assert(translations.size() == 1)
        assertEquals("acceso", translations.get(0).getText())
    }

    @Test
    void testTranslationsParse4() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/translation_advocate.json")
        def json = IOUtils.toString(resource, "UTF-8")
        def parser = new OxfordDictionaryParser("advocate")
        parser.parse(json)
        def results = parser.getResults()
        def result = results.get(0)
        assertEquals("advocate", result.getId())
        def lexicalEntries = result.getLexicalEntries()
        def entries = lexicalEntries.get(0).getEntries()
        def senses = entries.get(0).getSenses()
        def translations = senses.get(0).getTranslations()
        assert(translations.size() == 2)
        assertEquals("defensor", translations.get(0).getText())
    }
}
