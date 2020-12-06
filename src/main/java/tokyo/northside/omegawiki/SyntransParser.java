package tokyo.northside.omegawiki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SyntransParser {

    private String dmid;
    private List<OmegawikiMeaning> meanings = new ArrayList<>();

    public List<OmegawikiMeaning> getMeanings() {
        return meanings;
    }

    public void parse(final String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        node = node.get("ow_syntrans");
        if (node != null) {
            dmid = node.get("dmid").asText();
            node.fields().forEachRemaining(entry -> {
                if (entry.getKey().endsWith(".")) {
                    JsonNode meaning = entry.getValue();
                    try {
                        OmegawikiMeaning om = mapper.readValue(meaning.traverse(), OmegawikiMeaning.class);
                        if (om != null) {
                            meanings.add(om);
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
