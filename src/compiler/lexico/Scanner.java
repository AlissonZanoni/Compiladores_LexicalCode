package compiler.lexico;

import compiler.exceptions.LexicalException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class Scanner {

    private char[] content;
    private int estado;
    private int pos;
    private int contLine =1;
    private int flagOP =0;


    public Scanner(String filename){
        try {

            String txtConteudo;
            txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            System.out.println("DEBUG -----------");
            System.out.println(txtConteudo);
            System.out.println("DEBUG -----------");
            content = txtConteudo.toCharArray();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Token nextToken(){
        Token token;
        char currentChar;
        String term="";

        if(isEOF()){
            return null;
        }

        token = new Token();
        estado = 0;

        while(true){
            currentChar = nextChar();

            switch (estado){
                case 0:

                    if (isChar(currentChar)){

                        term +=currentChar;

                        while (nextChar() != ' '){
                            back();
                            term +=nextChar();
                        }

                        term = term.toLowerCase(Locale.ROOT);
                        if (term.matches("[a-z]*")){

                            if (term.equals("void")) {
                                token.setType(Token.TK_VOID);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("while")) {
                                token.setType(Token.TK_WHILE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("return")) {
                                token.setType(Token.TK_RETURN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("string")) {
                                token.setType(Token.TK_STRING);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("if")) {
                                token.setType(Token.TK_IF);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("float")) {
                                token.setType(Token.TK_FLOAT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("i")) {
                                token.setType(Token.TK_I);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("do")) {
                                token.setType(Token.TK_DO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("inicio")) {
                                token.setType(Token.TK_INICIO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("int")) {
                                token.setType(Token.TK_INT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("main")) {
                                token.setType(Token.TK_MAIN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("else")) {
                                token.setType(Token.TK_ELSE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("double")) {
                                token.setType(Token.TK_DOUBLE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("for")) {
                                token.setType(Token.TK_FOR);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("fim")) {
                                token.setType(Token.TK_FIM);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("cin")) {
                                token.setType(Token.TK_CIN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("cout")) {
                                token.setType(Token.TK_COUT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("char")) {
                                token.setType(Token.TK_CHAR);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else if (term.equals("callfuncao")) {
                                token.setType(Token.TK_CALLFUNCAO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else {
                                token.setType(Token.TK_IDENT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                        }

                        else {
                            throw new LexicalException("Palavra Desconhecida: "+term+" na linha "+contLine);
                        }


                    }

                    else if (isDigit(currentChar)) {

                        term +=currentChar;

                        while (nextChar() != ' '){
                            back();
                            term +=nextChar();
                        }

                        if (term.matches("[0-9]*")){
                            token.setType(Token.TK_NUMBER);
                            token.setText(term);
                            token.setLine(contLine);
                            back();
                            break;
                        }
                        else if (term.matches("[a-zA-Z__0-9]*")) {
                            throw new LexicalException("Simbolo Desconhecido: "+term+ " na linha "+contLine);
                        }
                        else if (term.matches("^\\d{1,9}(?:\\.\\d{1,2})?$")) {
                            token.setType(Token.TK_NUMBERFLOAT);
                            token.setText(term);
                            token.setLine(contLine);
                            back();
                            break;
                        }
                        else {
                            throw new LexicalException("Simbolo Desconhecido: "+term+ " na linha "+contLine);
                        }

                    }

                    else if (isSpace(currentChar)){
                        return token;
                    }

                    else if(isArqFinalLine(currentChar)){
                        break;
                    }

                    else if (isSimbols(currentChar) && flagOP==0) {
                            term +=currentChar;
                            char aux = nextChar();
                            flagOP++;

                            //MENOSMENOS
                            if(currentChar =='-' && aux == '-'){
                                token.setType(Token.TK_MENOSMENOS);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //MENOS
                            else if (currentChar =='-' && aux == ' ') {
                                token.setType(Token.TK_MENOS);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //MAISMAIS
                            else if(currentChar =='+' && aux == '+'){
                                token.setType(Token.TK_MAISMAIS);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //MAIS
                            else if (currentChar =='+' && aux == ' ') {
                                token.setType(Token.TK_MAIS);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //MAIORMAIOR
                            else if (currentChar =='>' && aux == '>') {
                                token.setType(Token.TK_MAIORMAIOR);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //MENORMENOR
                            else if (currentChar =='<' && aux == '<') {
                                token.setType(Token.TK_MENORMENOR);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //MAIOR
                            else if (currentChar =='>' && aux == ' ') {
                                token.setType(Token.TK_MAIOR);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //MENOR
                            else if (currentChar =='<' && aux == ' ') {
                                token.setType(Token.TK_MENOR);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }

                            //MAIORIGUAL
                            else if (currentChar =='>' && aux == '=') {
                                token.setType(Token.TK_MAIORIGUAL);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //MENORIGUAL
                            else if (currentChar =='<' && aux == '=') {
                                token.setType(Token.TK_MENORIGUAL);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //IGUALIGUAL
                            else if (currentChar == '=' && aux == '='){
                                token.setType(Token.TK_IGUALIGUAL);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //IGUAL
                            else if (currentChar == '=' && aux ==' ') {
                                token.setType(Token.TK_IGUAL);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CHAVEFECHADA
                            else if (currentChar == '}' && aux ==' ') {
                                token.setType(Token.TK_CHAVEFECHADA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CHAVEABERTA
                            else if (currentChar == '{' && aux ==' ') {
                                token.setType(Token.TK_CHAVEABERTA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CHAVEFECHADA
                            else if (currentChar == '}' && aux ==' ') {
                                token.setType(Token.TK_CHAVEFECHADA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //DOISPONTOS
                            else if (currentChar == ':' && aux ==' ') {
                                token.setType(Token.TK_DOISPONTO);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //BARRA
                            else if (currentChar == '/' && aux ==' ') {
                                token.setType(Token.TK_BARRA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //VIRGULA
                            else if (currentChar == ',' && aux ==' ') {
                                token.setType(Token.TK_VIRGULA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //ASTERISCO
                            else if (currentChar == '*' && aux ==' ') {
                                token.setType(Token.TK_ASTERISCO);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CONCHETEFECHADO
                            else if (currentChar == ')' && aux ==' ') {
                                token.setType(Token.TK_CONCHETESFECHADO);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CONCHETEABERTO
                            else if (currentChar == '(' && aux ==' ') {
                                token.setType(Token.TK_CONCHETESABERTO);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //BARRA
                            else if (currentChar == '/' && aux ==' ') {
                                token.setType(Token.TK_BARRA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //CIFRAO
                            else if (currentChar == '$' && aux ==' ') {
                                token.setType(Token.TK_CIFRAO);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            //DIFERENTE IGUAL
                            else if (currentChar == '!' && aux =='=') {
                                token.setType(Token.TK_DIFERENTEIGUAL);
                                token.setText(term+aux);
                                token.setLine(contLine);
                                flagOP=0;
                                break;
                            }
                            //BARRA
                            else if (currentChar == '/' && aux ==' ') {
                                token.setType(Token.TK_BARRA);
                                token.setText(term);
                                token.setLine(contLine);
                                flagOP=0;
                                back();
                                break;
                            }
                            else {
                                throw new LexicalException("Simbolo Desconhecido: "+term+aux+" na linha "+contLine);
                            }
                    }

                    else if (isEndLine(currentChar)){

                        term +=currentChar;

                        token.setType(Token.TK_PONTOFINAL);
                        token.setText(term);
                        token.setLine(contLine);

                        contLine++;
                        return token;

                    }

                    else {
                        throw new LexicalException("OPS NEM EINSTEIN ENTENDERIA ISSO ");
                    }
            }
        }
    }

    private boolean isDigit(char c){
        return c >= '0' && c<= '9';
    }

    private boolean isChar(char c){
        return (c >='a' && c<= 'z') || (c >='A' && c<= 'Z');
    }

    private boolean isSimbols(char c){
        return c=='-' || c=='+' || c=='<' || c=='>' || c=='*' || c=='/' || c=='=' || c=='{' || c=='}' || c==':' || c=='$' || c==',' || c=='(' || c==')' || c=='!' ;
    }

    private boolean isSpace(char c){
    return c ==' ';
    }

    private boolean isArqFinalLine(char c){
        return c=='\r' || c=='\n';
    }

    private boolean isEndLine(char c){
        return c==';';
    }

    private char nextChar(){
        return  content[pos++];
    }

    private boolean isEOF(){
        return pos == content.length;
    }

    private char back(){
        return content[pos--];
    }
}
