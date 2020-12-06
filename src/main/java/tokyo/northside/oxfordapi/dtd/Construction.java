package tokyo.northside.oxfordapi.dtd;

public class Construction {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Construction{" + "text='" + text + '\'' + '}';
    }
}
