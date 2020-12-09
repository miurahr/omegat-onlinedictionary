package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Inflection {
    private List<Domain> domains;
    private List<GrammaticalFeature> grammaticalFeatures;
    private String inflectedForm;
    private LexicalCategory lexicalCategory;
    private List<Pronunciation> pronunciations;
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

    public LexicalCategory getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(LexicalCategory lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
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

    public String getInflectedForm() {
        return inflectedForm;
    }

    public void setInflectedForm(String inflectedForm) {
        this.inflectedForm = inflectedForm;
    }

    @Override
    public String toString() {
        return "Inflection{" +
                "domains=" + domains +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", inflectedForm='" + inflectedForm + '\'' +
                ", lexicalCategory=" + lexicalCategory +
                ", pronounciations=" + pronunciations +
                ", regions=" + regions +
                ", registers=" + registers +
                '}';
    }
}
