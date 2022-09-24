package compiler.lexico;

public class Token {

    public static final int TK_IDENT = 0;
    public static final int TK_NUMBER = 1;
    public static final int TK_OPERATOR = 2;
    public static final int TK_PONCTUATION = 3;
    public static final int TK_ASSING = 4;
    public static final int TK_PONTOFINAL = 5;

    private int type;
    private String text;

    private int line;


    public Token(int type, String text, int line){
        super();
        this.type = type;
        this.text = text;
        this.line = line;
    }

    public Token(){
        super();
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return "Token [type="+ type + ", text= " +text +", line= "+line +"]";
    }

}
