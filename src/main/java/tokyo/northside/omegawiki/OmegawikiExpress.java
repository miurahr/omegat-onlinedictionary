package tokyo.northside.omegawiki;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class OmegawikiExpress {
    private String expression;
    private Map<String, OmegawikiDefinition> omegawikiDefinitions = new HashMap<>();

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @JsonAnyGetter
    public Map<String, OmegawikiDefinition> getOmegawikiDefinitions() {
        return omegawikiDefinitions;
    }

    @JsonAnySetter
    public void setOmegawikiDefinitions(Map<String, OmegawikiDefinition> omegawikiDefinitions) {
        this.omegawikiDefinitions = omegawikiDefinitions;
    }

    @Override
    public String toString() {
        return "OmegawikiExpress{" +
                "expression='" + expression + '\'' +
                ", omegawikiDefinitions=" + omegawikiDefinitions +
                '}';
    }

}
