package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Subsense {
    private List<String> definitions;
    private List<DomainClass> domainClasses;
    private String id;
    private List<SemanticClass> semanticClasses;
    private List<String> shortDefinitions;
    private List<Domain> domains;
    private List<Example> examples;
    private List<Register> registers;
    private List<Region> regions;
    private List<Note> notes;

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<DomainClass> getDomainClasses() {
        return domainClasses;
    }

    public void setDomainClasses(List<DomainClass> domainClasses) {
        this.domainClasses = domainClasses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SemanticClass> getSemanticClasses() {
        return semanticClasses;
    }

    public void setSemanticClasses(List<SemanticClass> semanticClasses) {
        this.semanticClasses = semanticClasses;
    }

    public List<String> getShortDefinitions() {
        return shortDefinitions;
    }

    public void setShortDefinitions(List<String> shortDefinitions) {
        this.shortDefinitions = shortDefinitions;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Subsense{" +
                "definitions=" + definitions +
                ", domainClasses=" + domainClasses +
                ", id='" + id + '\'' +
                ", semanticClasses=" + semanticClasses +
                ", shortDefinitions=" + shortDefinitions +
                ", domains=" + domains +
                ", examples=" + examples +
                ", registers=" + registers +
                ", regions=" + regions +
                ", notes=" + notes +
                '}';
    }
}
