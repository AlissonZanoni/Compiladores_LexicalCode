package compiler.lexico;

public class Token {

    public static final int TK_WHILE = 1;
    public static final int TK_VOID = 2;
    public static final int TK_STRING = 3;
    public static final int TK_RETURN = 4;
    public static final int TK_NUMBER = 5;
    public static final int TK_NUMBERFLOAT = 6;
    public static final int TK_IDENT = 7;
    public static final int TK_NOMEDOCHAR = 8;

    //public static final int TK_NOMEDAVARIAVEL = 9; não foi necessário.

    public static final int TK_NOMEDASTRING = 10;
    public static final int TK_MAIN = 11;
    public static final int TK_LITERAL = 12;
    public static final int TK_INTEGER = 13;
    public static final int TK_INT = 14;
    public static final int TK_INICIO = 15;
    public static final int TK_IF = 16;
    public static final int TK_I = 17;
    public static final int TK_FOR = 18;
    public static final int TK_FLOAT = 19;
    public static final int TK_FIM = 20;
    public static final int TK_ELSE = 21;
    public static final int TK_DOUBLE = 22;
    public static final int TK_DO = 23;
    public static final int TK_COUT = 24;
    public static final int TK_CIN = 25;
    public static final int TK_CHAR = 26;
    public static final int TK_CALLFUNCAO = 27;
    public static final int TK_MAIORMAIOR = 28;
    public static final int TK_MAIORIGUAL = 29;
    public static final int TK_MAIOR = 30;
    public static final int TK_IGUALIGUAL = 31;
    public static final int TK_IGUAL = 32;
    public static final int TK_MENORIGUAL = 33;
    public static final int TK_MENORMENOR = 34;
    public static final int TK_MENOR = 35;
    public static final int TK_MAISMAIS = 36;
    public static final int TK_MAIS = 37;
    public static final int TK_CHAVEFECHADA = 38;
    public static final int TK_CHAVEABERTA = 39;
    public static final int TK_PONTOFINAL = 40;
    public static final int TK_DOISPONTO = 41;
    public static final int TK_BARRA = 42;
    public static final int TK_VIRGULA = 43;
    public static final int TK_ASTERISCO = 44;
    public static final int TK_CONCHETESFECHADO = 45;
    public static final int TK_CONCHETESABERTO = 46;
    public static final int TK_CIFRAO = 47;
    public static final int TK_DIFERENTEIGUAL = 48;
    public static final int TK_MENOSMENOS = 49;
    public static final int TK_MENOS = 50;


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
        return "[Token="+ type + ", text= " +text +", line= "+line +"]";
    }
}
