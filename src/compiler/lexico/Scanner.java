package compiler.lexico;

import compiler.exceptions.LexicalException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Scanner {

    private char[] content;
    private int estado;
    private int pos;

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
                        break;
                    } else if (isDigit(currentChar)) {
                        term +=currentChar;

                        token.setType(Token.TK_NUMBER);
                        token.setText(term);
                        break;
                    }
                    else if (isSpace(currentChar)){
                        estado=200;
                        return token;
                    }
                    else if(isArqFinalLine(currentChar)){
                        break;
                    }
                    else if (isOperator(currentChar)){
                        term +=currentChar;

                        token.setType(Token.TK_OPERATOR);
                        token.setText(term);
                    }
                    else if (isEndLine(currentChar)){
                        estado=200;
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

    private boolean isOperator(char c){
        return c =='>' || c== '<' || c=='=' || c== '/' || c=='*' || c=='+' || c=='-';
    }

    private boolean isSpace(char c){
    return c ==' ';
    }

    private boolean isPontoFinal(char c){
        return c==';';
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

    private void back(){
        pos--;
    }
}
