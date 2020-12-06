package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Sense {
    private List<String> definitions;
    private List<DomainClass> domainClasses;
    private List<Example> examples;
    private String id;
    private List<SemanticClass> semanticClasses;
    private List<String> shortDefinitions;
    private List<Subsense> subsenses;
    private List<Synonym> synonyms;
    private List<ThesaurusLink> thesaurusLinks;
    @JsonIgnore
    private List<Register> registers;
    @JsonIgnore
    private List<Region> regions;
    @JsonIgnore
    private List<String> crossReferenceMarkers;
    @JsonIgnore
    private List<CrossReference> crossReferences;
    @JsonIgnore
    private List<Inflection> inflections;

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

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
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

    public List<Subsense> getSubsenses() {
        return subsenses;
    }

    public void setSubsenses(List<Subsense> subsenses) {
        this.subsenses = subsenses;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public List<ThesaurusLink> getThesaurusLinks() {
        return thesaurusLinks;
    }

    public void setThesaurusLinks(List<ThesaurusLink> thesaurusLinks) {
        this.thesaurusLinks = thesaurusLinks;
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

    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<CrossReference> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(List<CrossReference> crossReferences) {
        this.crossReferences = crossReferences;
    }

    @Override
    public String toString() {
        return "Sense{" +
                "definitions=" + definitions +
                ", domainClasses=" + domainClasses +
                ", examples=" + examples +
                ", id='" + id + '\'' +
                ", semanticClasses=" + semanticClasses +
                ", shortDefinitions=" + shortDefinitions +
                ", subsenses=" + subsenses +
                ", synonyms=" + synonyms +
                ", thesaurusLinks=" + thesaurusLinks +
                ", registers=" + registers +
                ", regions=" + regions +
                '}';
    }
}
