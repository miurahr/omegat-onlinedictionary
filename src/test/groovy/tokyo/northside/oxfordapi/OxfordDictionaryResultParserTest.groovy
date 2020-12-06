package tokyo.northside.oxfordapi

import org.apache.commons.io.IOUtils
import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.Assert.*

class OxfordDictionaryResultParserTest {
    @Test
    void testParse() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("ace")
        parser.parse(json)
        List<Result> result = parser.getResults()
        Result entry = result.get(0)
        assertEquals("ace", entry.getId())

    }
}
