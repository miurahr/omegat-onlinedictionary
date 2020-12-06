package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Inflection {
    private List<Domain> domains;
    private List<GrammaticalFeature> grammaticalFeatures;
    private String infectedForm;
    private LexicalCategory lexicalCategory;
    private List<Pronounciation> pronounciations;
    private List<Region> regions;
    private List<Register> registers;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public String getInfectedForm() {
        return infectedForm;
    }

    public void setInfectedForm(String infectedForm) {
        this.infectedForm = infectedForm;
    }

    public LexicalCategory getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(LexicalCategory lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

    public List<Pronounciation> getPronounciations() {
        return pronounciations;
    }

    public void setPronounciations(List<Pronounciation> pronounciations) {
        this.pronounciations = pronounciations;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "Inflection{" +
                "domains=" + domains +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", infectedForm='" + infectedForm + '\'' +
                ", lexicalCategory=" + lexicalCategory +
                ", pronounciations=" + pronounciations +
                ", regions=" + regions +
                ", registers=" + registers +
                '}';
    }
}
