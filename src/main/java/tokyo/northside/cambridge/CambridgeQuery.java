package tokyo.northside.cambridge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tokyo.northside.cambridge.dtd.Entry;

import java.io.IOException;
import java.util.List;

public class CambridgeQuery {

    private List<Entry> results;

    public void parse(final String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        results = mapper.readValue(node.traverse(), new TypeReference<List<Entry>>() {
        });
    }
}
