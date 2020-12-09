package tokyo.northside.oxfordapi

import org.apache.commons.io.IOUtils
import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Entry
import tokyo.northside.oxfordapi.dtd.LexicalEntry
import tokyo.northside.oxfordapi.dtd.Result
import tokyo.northside.oxfordapi.dtd.Sense

import static org.junit.Assert.*

class OxfordDictionaryEntriesTest {
    @Test
    void testParse1() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_result1.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("ace")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("ace", entry.getId())

    }

    @Test
    void testParse2() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_result2.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("software")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("software", entry.getId())

    }

    @Test
    void testParse3() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_advocate.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("software")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("advocate", entry.getId())

    }

    @Test
    void testParse4() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_result4.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("freedom")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("freedom", entry.getId())

    }

    @Test
    void testParse5() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_result5.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("documentation")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("documentation", entry.getId())
    }

    @Test
    void testParse6() {
        InputStream resource = OxfordDictionaryParser.class.getClassLoader().getResourceAsStream("oxfordapi/entry_result6.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryParser parser = new OxfordDictionaryParser("typographic")
        parser.parse(json)
        List<Result> results = parser.getResults()
        for (Result result : results) {
            for (LexicalEntry lexicalEntry : result.getLexicalEntries()) {
                for (Entry entry: lexicalEntry.getEntries()) {
                    for (Sense sense : entry.getSenses()) {
                        assertFalse(sense.getDefinitions() != null)
                    }
                }
            }
        }
    }

}