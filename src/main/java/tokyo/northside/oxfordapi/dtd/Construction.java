package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Construction {
    private String text;
    private List<Example> examples;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    @Override
    public String toString() {
        return "Construction{" + "text='" + text + '\'' + ", examples=" + examples + '}';
    }
}
