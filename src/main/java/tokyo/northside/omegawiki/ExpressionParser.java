package tokyo.northside.omegawiki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {

    private String word;
    private List<OmegawikiDefinition> definitions = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public List<OmegawikiDefinition> getDefinitions() {
        return definitions;
    }

    public void parse(final String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        node = node.get("ow_express");
        if (node != null) {
            JsonNode expression = node.get("expression");
            if (expression == null) {
                return;
            }
            word = expression.asText();
            node.fields().forEachRemaining(entry -> {
                if (entry.getKey().startsWith("ow_define_")) {
                    JsonNode definition = entry.getValue();
                    ObjectMapper om = new ObjectMapper();
                    try {
                        OmegawikiDefinition od = om.readValue(definition.traverse(), OmegawikiDefinition.class);
                        if (od != null) {
                            definitions.add(od);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            return;
        }
    }
}
