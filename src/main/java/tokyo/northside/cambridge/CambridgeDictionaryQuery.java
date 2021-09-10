package tokyo.northside.cambridge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tokyo.northside.cambridge.dtd.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CambridgeDictionaryQuery {

    private List<Dictionary> dictionaries = new ArrayList<>();

    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }



    public void parse(final String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        dictionaries = mapper.readValue(node.traverse(), new TypeReference<List<Dictionary>>() {
        });
    }
}
