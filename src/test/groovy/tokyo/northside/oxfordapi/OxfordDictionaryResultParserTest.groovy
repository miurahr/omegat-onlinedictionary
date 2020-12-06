package tokyo.northside.oxfordapi

import org.apache.commons.io.IOUtils
import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.Assert.*

class OxfordDictionaryResultParserTest {
    @Test
    void testParse1() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("ace")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("ace", entry.getId())

    }

    @Test
    void testParse2() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result2.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("software")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("software", entry.getId())

    }

    @Test
    void testParse3() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result3.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("software")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("advocate", entry.getId())

    }

    @Test
    void testParse4() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result4.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("freedom")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("freedom", entry.getId())

    }

    @Test
    void testParse5() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result5.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("documentation")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("documentation", entry.getId())
    }

}