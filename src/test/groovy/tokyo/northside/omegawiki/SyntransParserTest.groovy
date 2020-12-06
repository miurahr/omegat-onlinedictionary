package tokyo.northside.omegawiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils;
import org.junit.Test
import tokyo.northside.omegawiki.dtd.OmegawikiMeaning

import static org.junit.Assert.*

class SyntransParserTest {

    @Test
    void parseMeaningTest() throws IOException {
        String json = "{\"1.\":{\"langid\":\"84\",\"lang\":\"Bulgarian\",\"e\":\"\\u0430\\u0437\\u0431\\u0443\\u043a\\u0430\",\"im\":\"1\"}}"
        ObjectMapper mapper = new ObjectMapper()
        JsonNode node = mapper.readTree(json)
        Map.Entry<String, JsonNode> entry = node.fields().next()
        String key = entry.getKey()
        assertEquals(key, "1.")
        JsonNode meaning = entry.getValue()
        OmegawikiMeaning omegawikiMeaning = mapper.readValue(meaning.traverse(), OmegawikiMeaning.class)
        assertEquals(omegawikiMeaning.toString(), "{langid='84', lang='Bulgarian', e='\u0430\u0437\u0431\u0443\u043a\u0430', im='1'}")
    }

    @Test
    void parseSyntransTest() throws IOException{
        InputStream resource = SyntransParser.class.getClassLoader().getResourceAsStream("syntrans_result.json")
        String json = IOUtils.toString(resource, "UTF-8")
        SyntransParser parser = new SyntransParser()
        parser.parse(json)
        List<OmegawikiMeaning> meanings = parser.getMeanings()
        assertEquals(meanings.get(0).getE(), "азбука")
    }
}