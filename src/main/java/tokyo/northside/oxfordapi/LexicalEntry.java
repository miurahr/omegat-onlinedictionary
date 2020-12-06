package tokyo.northside.oxfordapi;

import java.util.List;

public class LexicalEntry {
    public List<Compound> getCompounds() {
        return compounds;
    }

    public void setCompounds(final List<Compound> compounds) {
        this.compounds = compounds;
    }

    private List<Compound> compounds;


}
