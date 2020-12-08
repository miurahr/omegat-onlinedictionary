package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class ToneGroup {
    private List<Tone> tones;

    public List<Tone> getTones() {
        return tones;
    }

    public void setTones(List<Tone> tones) {
        this.tones = tones;
    }

    @Override
    public String toString() {
        return "ToneGroup{" + "tones=" + tones + '}';
    }
}
