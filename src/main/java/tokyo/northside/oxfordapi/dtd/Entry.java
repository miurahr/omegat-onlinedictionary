package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gen.core.tbx.P;

import java.util.List;

public class Entry {
    private List<String> etymologies;
    private String homographNumber;
    private List<Pronounciation> pronunciations;
    private List<Sense> senses;
    private List<GrammaticalFeature> grammaticalFeatures;
    @JsonIgnore
    private List<Inflection> inflections;
    @JsonIgnore
    private List<CrossReference> crossReferences;
    @JsonIgnore
    private List<String> crossReferenceMarkers;
    @JsonIgnore
    private List<Note> notes;

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

    public List<Pronounciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronounciation> pronunciations) {
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
}
