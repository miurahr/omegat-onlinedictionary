package tokyo.northside.omegawiki;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test
import static org.junit.Assert.*

class ExpressionParserTest {

    @Test
    void definitionsTest() throws IOException {
        String json = "{\"ow_define_1\":{\"dmid\":\"6692\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"tattle\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To tell what others do wrong.\"}},\"ow_define_2\":{\"dmid\":\"751478\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"accuse\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To lay a charge against; bring an accusation against.\"}},\"ow_define_3\":{\"dmid\":\"819321\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"arraign\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To bring before a court to answer to an indictment.\"}},\"ow_define_4\":{\"dmid\":\"837820\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"betray\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To give away information about somebody.\"}},\"ow_define_5\":{\"dmid\":\"1503181\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"charge\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To blame for, make a claim of wrongdoing against.\"}},\"ow_define_6\":{\"dmid\":\"1556344\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"indict\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To accuse formally of a crime.\"}}}"
        ObjectMapper mapper = new ObjectMapper()
        JsonNode node = mapper.readTree(json)
        Map<String, OmegawikiDefinition> definitions = mapper.readValue(node.traverse(),
                new TypeReference<Map<String, OmegawikiDefinition>>() {
                })
        //assertEquals( "{ow_define_1=Definition{dmid=6692, langid=87, lang='español', definition={spelling='tattle', langid=85, lang='English', text='To tell what others do wrong.'}}, ow_define_2=Definition{dmid=751478, langid=87, lang='español', definition={spelling='accuse', langid=85, lang='English', text='To lay a charge against; bring an accusation against.'}}, ow_define_3=Definition{dmid=819321, langid=87, lang='español', definition={spelling='arraign', langid=85, lang='English', text='To bring before a court to answer to an indictment.'}}, ow_define_4=Definition{dmid=837820, langid=87, lang='español', definition={spelling='betray', langid=85, lang='English', text='To give away information about somebody.'}}, ow_define_5=Definition{dmid=1503181, langid=87, lang='español', definition={spelling='charge', langid=85, lang='English', text='To blame for, make a claim of wrongdoing against.'}}, ow_define_6=Definition{dmid=1556344, langid=87, lang='español', definition={spelling='indict', langid=85, lang='English', text='To accuse formally of a crime.'}}}",
        //        definitions.toString())
    }

    @Test
    void parserMultipleDefineTest() throws IOException {
        ExpressionParser expressionParser = new ExpressionParser()
        String json = "{\"ow_express\":{\"expression\":\"acusar\",\"ow_define_1\":{\"dmid\":\"6692\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"tattle\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To tell what others do wrong.\"}},\"ow_define_2\":{\"dmid\":\"751478\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"accuse\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To lay a charge against; bring an accusation against.\"}},\"ow_define_3\":{\"dmid\":\"819321\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"arraign\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To bring before a court to answer to an indictment.\"}},\"ow_define_4\":{\"dmid\":\"837820\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"betray\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To give away information about somebody.\"}},\"ow_define_5\":{\"dmid\":\"1503181\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"charge\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To blame for, make a claim of wrongdoing against.\"}},\"ow_define_6\":{\"dmid\":\"1556344\",\"langid\":\"87\",\"lang\":\"espa\\u00f1ol\",\"definition\":{\"spelling\":\"indict\",\"langid\":\"85\",\"lang\":\"English\",\"text\":\"To accuse formally of a crime.\"}}}}"
        expressionParser.parse(json)
        assertEquals(expressionParser.getWord(), "acusar")
        List<OmegawikiDefinition> definitions= expressionParser.getDefinitions()
        assertEquals("6692", definitions.get(0).getDmid())
        assertEquals( "To tell what others do wrong.", definitions.get(0).getDefinition().getText())
        assertEquals( "751478", definitions.get(1).getDmid())
        assertEquals( "To lay a charge against; bring an accusation against.", definitions.get(1).getDefinition().getText())
    }

}