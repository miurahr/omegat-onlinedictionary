package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Example {
    @JsonIgnore
    private List<Register> registers;
    private String text;
    @JsonIgnore
    private List<Note> notes;

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Example{" + "registers=" + registers + ", text='" + text + '\'' + ", notes=" + notes + '}';
    }
}
