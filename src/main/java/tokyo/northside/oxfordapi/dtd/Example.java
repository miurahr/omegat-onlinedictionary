package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Example {
    @JsonIgnore
    private List<Register> registers;
    private String text;

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

    @Override
    public String toString() {
        return "Example{" + "registers=" + registers + ", text='" + text + '\'' + '}';
    }
}
