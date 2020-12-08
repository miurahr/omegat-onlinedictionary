package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Compound {
    private List<Domain> domains;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    @Override
    public String toString() {
        return "Compound{" + "domains=" + domains + '}';
    }
}
