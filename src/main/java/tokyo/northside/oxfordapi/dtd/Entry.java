package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Entry {
    private List<String> etymologies;
    private String homographNumber;
    private List<Pronounciation> pronunciations;
    private List<Sense> senses;
    private List<GrammaticalFeature> grammaticalFeatures;

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

}
