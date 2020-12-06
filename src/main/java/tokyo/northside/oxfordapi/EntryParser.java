package tokyo.northside.oxfordapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntryParser {

    private String word;
    private List<Entry> results = new ArrayList<>();

    public EntryParser(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public List<Entry> getResults() {
        return results;
    }

    public void parse(final String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        node = node.get("results");
        results = mapper.readValue(node.traverse(), new TypeReference<List<Entry>>() {
        });
    }
}
