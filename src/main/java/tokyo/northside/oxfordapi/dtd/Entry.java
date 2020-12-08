package tokyo.northside.oxfordapi.dtd;


import java.util.List;

public class Entry {
    private List<String> etymologies;
    private String homographNumber;
    private List<Pronunciation> pronunciations;
    private List<Sense> senses;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Inflection> inflections;
    private List<CrossReference> crossReferences;
    private List<String> crossReferenceMarkers;
    private List<Note> notes;
    private List<VariantForm> variantForms;

    public List<String> getEtymologies() {
        return etymologies;
    }

    public void setEtymologies(List<String> etymologies) {
        this.etymologies = etymologies;
    }

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public List<Inflection> getInflections() {
        return inflections;
    }

    public void setInflections(List<Inflection> inflections) {
        this.inflections = inflections;
    }

    public List<CrossReference> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(List<CrossReference> crossReferences) {
        this.crossReferences = crossReferences;
    }

    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "etymologies=" + etymologies +
                ", homographNumber='" + homographNumber + '\'' +
                ", pronunciations=" + pronunciations +
                ", senses=" + senses +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", inflections=" + inflections +
                ", crossReferences=" + crossReferences +
                ", crossReferenceMarkers=" + crossReferenceMarkers +
                ", notes=" + notes +
                ", variantForms=" + variantForms +
                '}';
    }

    public void setVariantForms(List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }
}
