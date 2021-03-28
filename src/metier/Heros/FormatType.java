package metier.Heros;

import java.lang.reflect.Method;

public class FormatType {
    public String text,type;
    public Method setter;

    public FormatType(String text,String type, Method value) {
        this.text = text;
        this.setter = value;
        this.type = type;
    }

}