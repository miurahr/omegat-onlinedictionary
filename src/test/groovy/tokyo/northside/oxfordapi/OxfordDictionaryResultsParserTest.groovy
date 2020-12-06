package tokyo.northside.oxfordapi

import org.apache.commons.io.IOUtils
import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Results

import static org.junit.Assert.*

class OxfordDictionaryResultsParserTest {
    @Test
    void testParse() {
        InputStream resource = OxfordDictionaryEntryParser.class.getClassLoader().getResourceAsStream("oxfordapi_entry_result.json")
        String json = IOUtils.toString(resource, "UTF-8")
        OxfordDictionaryEntryParser parser = new OxfordDictionaryEntryParser("ace")
        parser.parse(json)
        List<Results> result = parser.getResults()
        Results entry = result.get(0)
        assertEquals("ace", entry.getId())

    }
}
