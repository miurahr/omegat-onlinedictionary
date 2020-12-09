package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private List<Synonym> synonyms;
    private List<ThesaurusLink> thesaurusLinks;
    private List<Construction> constructions;
    private List<CrossReference> crossReferences;
    private List<String> crossReferenceMarkers;
    private List<VariantForm> variantForms;
    private List<Translation> translations;

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

    public List<Construction> getConstructions() {
        return constructions;
    }

    public void setConstructions(List<Construction> constructions) {
        this.constructions = constructions;
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

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
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
