package compiler.lexico;

import compiler.exceptions.LexicalException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        estado =0;

        while(true){
            currentChar = nextChar();

            switch (estado){
                case 0:

                    if (isChar(currentChar)){
                        term +=currentChar;

                        token.setType(Token.TK_IDENT);
                        token.setText(term);
                        token.setLine(contLine);

                        break;
                    } else if (isDigit(currentChar)) {
                        term +=currentChar;

                        token.setType(Token.TK_NUMBER);
                        token.setText(term);
                        token.setLine(contLine);
                        break;
                    }
                    else if (isSpace(currentChar)){
                        return token;
                    }
                    else if(isArqFinalLine(currentChar)){

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
                        throw new LexicalException("Simbolo Desconhecido, fala em portugues fi!");
                    }
                    break;
            }
        }
    }

    private boolean isDigit(char c){
        return c >= '0' && c<= '9';
    }

    private boolean isChar(char c){
        return (c >='a' && c<= 'z') || (c >='A' && c<= 'Z');
    }

//    private boolean isMenos(char c){
//        return c=='-';
//    }

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
