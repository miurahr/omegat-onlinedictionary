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

}