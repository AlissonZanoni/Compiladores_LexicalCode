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
                        char proxLetra = nextChar();

                        while (proxLetra != ' ' && proxLetra != '\n' && proxLetra != '\r' ){
                            term +=proxLetra;
                            proxLetra = nextChar();

                        }

                        term = term.toLowerCase(Locale.ROOT);
                        if (term.matches("[a-z]*")){

                            if (term.equals("void")) {
                                token.setType(Token.TK_VOID);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("while")) {
                                token.setType(Token.TK_WHILE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("return")) {
                                token.setType(Token.TK_RETURN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("string")) {
                                token.setType(Token.TK_STRING);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("if")) {
                                token.setType(Token.TK_IF);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("float")) {
                                token.setType(Token.TK_FLOAT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
//                            else if (term.equals("i")) {
//                                token.setType(Token.TK_I);
//                                token.setText(term);
//                                token.setLine(contLine);
//                                back();
//                                break;
//                            }
                            else if (term.equals("do")) {
                                token.setType(Token.TK_DO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("inicio")) {
                                token.setType(Token.TK_INICIO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("int")) {
                                token.setType(Token.TK_INT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("main")) {
                                token.setType(Token.TK_MAIN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("else")) {
                                token.setType(Token.TK_ELSE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("double")) {
                                token.setType(Token.TK_DOUBLE);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("for")) {
                                token.setType(Token.TK_FOR);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("fim")) {
                                token.setType(Token.TK_FIM);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("cin")) {
                                token.setType(Token.TK_CIN);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("cout")) {
                                token.setType(Token.TK_COUT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("char")) {
                                token.setType(Token.TK_CHAR);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("callfuncao")) {
                                token.setType(Token.TK_CALLFUNCAO);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if (term.equals("integer")) {
                                token.setType(Token.TK_INTEGER);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else if(term.length() <= 15){
                                token.setType(Token.TK_IDENT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else {
                                throw new LexicalException("Identificador possui mais de 15 digitos: "+term+" na linha "+contLine);
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

                            if (Integer.parseInt(term) <= 100){
                                token.setType(Token.TK_NUMBER);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                return token;
                            }
                            else {
                                throw new LexicalException("Número inteiro não pode ser maior que 100: "+term+ " na linha "+contLine);
                            }

                        }
                        else if (term.matches("[a-zA-Z__0-9]*")) {
                            throw new LexicalException("Simbolo Desconhecido: "+term+ " na linha "+contLine);
                        }
                        else if (term.matches("^\\d{1,9}(?:\\.\\d{1,2})?$")) {

                            if (Float.parseFloat(term) <= 100){
                                token.setType(Token.TK_NUMBERFLOAT);
                                token.setText(term);
                                token.setLine(contLine);
                                back();
                                break;
                            }
                            else {
                                throw new LexicalException("Número float maior que 100: "+term+ " na linha "+contLine);
                            }
                        }
                        else {
                            throw new LexicalException("Simbolo Desconhecido: "+term+ " na linha "+contLine);
                        }

                    }

                    else if (isSpace(currentChar)){
                        break;
                    }

                    else if(isBarraN(currentChar)){
                        contLine++;
                        break;
                    }

                    else if(isBarraR(currentChar)){
                        break;
                    }

                    else if (isSimbols(currentChar) && flagOP==0) {
                            term +=currentChar;


                            //Verificar se está no final do arquivo para não chamar a função nextchar e quebrar o sistema
                          if(isEOF()){
                             if (currentChar == '/') {
                                  token.setType(Token.TK_BARRA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                             else if (currentChar =='-') {
                                 token.setType(Token.TK_MENOS);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }

                             else if (currentChar =='+') {
                                 token.setType(Token.TK_MAIS);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }

                             else if (currentChar =='>') {
                                 token.setType(Token.TK_MAIOR);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //MENOR
                             else if (currentChar =='<') {
                                 token.setType(Token.TK_MENOR);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }

                             else if (currentChar == '=') {
                                 token.setType(Token.TK_IGUAL);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //CHAVEFECHADA
                             else if (currentChar == '}') {
                                 token.setType(Token.TK_CHAVEFECHADA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //CHAVEABERTA
                             else if (currentChar == '{') {
                                 token.setType(Token.TK_CHAVEABERTA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //CHAVEFECHADA
                             else if (currentChar == '}') {
                                 token.setType(Token.TK_CHAVEFECHADA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //DOISPONTOS
                             else if (currentChar == ':') {
                                 token.setType(Token.TK_DOISPONTO);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //BARRA
                             else if (currentChar == '/') {
                                 token.setType(Token.TK_BARRA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //VIRGULA
                             else if (currentChar == ',') {
                                 token.setType(Token.TK_VIRGULA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //ASTERISCO
                             else if (currentChar == '*') {
                                 token.setType(Token.TK_ASTERISCO);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //CONCHETEFECHADO
                             else if (currentChar == ')') {
                                 token.setType(Token.TK_CONCHETESFECHADO);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 break;
                             }
                             //CONCHETEABERTO
                             else if (currentChar == '(') {
                                 token.setType(Token.TK_CONCHETESABERTO);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //BARRA
                             else if (currentChar == '/') {
                                 token.setType(Token.TK_BARRA);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }
                             //CIFRAO
                             else if (currentChar == '$') {
                                 token.setType(Token.TK_CIFRAO);
                                 token.setText(term);
                                 token.setLine(contLine);
                                 flagOP=0;
                                 return token;
                             }


                          }
                          else {
                              char aux = nextChar();
                              flagOP++;

                              //MENOSMENOS
                              if(currentChar =='-' && aux == '-'){
                                  token.setType(Token.TK_MENOSMENOS);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //MENOS
                              else if (currentChar =='-') {
                                  token.setType(Token.TK_MENOS);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //MAISMAIS
                              else if(currentChar =='+' && aux == '+'){
                                  token.setType(Token.TK_MAISMAIS);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //MAIS
                              else if (currentChar =='+') {
                                  token.setType(Token.TK_MAIS);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //MAIORMAIOR
                              else if (currentChar =='>' && aux == '>') {
                                  token.setType(Token.TK_MAIORMAIOR);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //MENORMENOR
                              else if (currentChar =='<' && aux == '<') {
                                  token.setType(Token.TK_MENORMENOR);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //MAIOR
                              else if (currentChar =='>') {
                                  token.setType(Token.TK_MAIOR);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //MENOR
                              else if (currentChar =='<') {
                                  token.setType(Token.TK_MENOR);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }

                              //MAIORIGUAL
                              else if (currentChar =='>' && aux == '=') {
                                  token.setType(Token.TK_MAIORIGUAL);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //MENORIGUAL
                              else if (currentChar =='<' && aux == '=') {
                                  token.setType(Token.TK_MENORIGUAL);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //IGUALIGUAL
                              else if (currentChar == '=' && aux == '='){
                                  token.setType(Token.TK_IGUALIGUAL);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //IGUAL
                              else if (currentChar == '=') {
                                  token.setType(Token.TK_IGUAL);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CHAVEFECHADA
                              else if (currentChar == '}') {
                                  token.setType(Token.TK_CHAVEFECHADA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CHAVEABERTA
                              else if (currentChar == '{') {
                                  token.setType(Token.TK_CHAVEABERTA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CHAVEFECHADA
                              else if (currentChar == '}') {
                                  token.setType(Token.TK_CHAVEFECHADA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //DOISPONTOS
                              else if (currentChar == ':') {
                                  token.setType(Token.TK_DOISPONTO);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //BARRA
                              else if (currentChar == '/') {
                                  token.setType(Token.TK_BARRA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //VIRGULA
                              else if (currentChar == ',') {
                                  token.setType(Token.TK_VIRGULA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //ASTERISCO
                              else if (currentChar == '*') {
                                  token.setType(Token.TK_ASTERISCO);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CONCHETEFECHADO
                              else if (currentChar == ')') {
                                  token.setType(Token.TK_CONCHETESFECHADO);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CONCHETEABERTO
                              else if (currentChar == '(') {
                                  token.setType(Token.TK_CONCHETESABERTO);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //BARRA
                              else if (currentChar == '/') {
                                  token.setType(Token.TK_BARRA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //CIFRAO
                              else if (currentChar == '$') {
                                  token.setType(Token.TK_CIFRAO);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              //DIFERENTE IGUAL
                              else if (currentChar == '!' && aux =='=') {
                                  token.setType(Token.TK_DIFERENTEIGUAL);
                                  token.setText(term+aux);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  return token;
                              }
                              //BARRA
                              else if (currentChar == '/') {
                                  token.setType(Token.TK_BARRA);
                                  token.setText(term);
                                  token.setLine(contLine);
                                  flagOP=0;
                                  back();
                                  return token;
                              }
                              else {
                                  throw new LexicalException("Simbolo Desconhecido: "+term+aux+" na linha "+contLine);
                              }
                          }

                    }

                    else if(isValorChar(currentChar)){

                        while (nextChar() != '&'){
                            back();
                            term +=nextChar();
                        }


                        if (term.length()== 1){
                            token.setType(Token.TK_NOMEDOCHAR);
                            token.setText(term);
                            token.setLine(contLine);
                        }else {
                            throw new LexicalException("Char com tamanho inválido: "+term+" na linha "+contLine);
                        }
                    }

                    else if(isValorString(currentChar)){

                        while (nextChar() != '#'){
                            back();
                            term +=nextChar();
                        }

                        token.setText(term);
                        token.setType(Token.TK_NOMEDASTRING);
                        token.setLine(contLine);
                        return token;
                    }

                    else if(isLiteral(currentChar)){

                        term +=currentChar;

                        while (nextChar() != '"'){
                            back();
                            term +=nextChar();
                        }

                        token.setText(term+'"');
                        token.setType(Token.TK_LITERAL);
                        token.setLine(contLine);
                        return token;
                    }

                    else if (isComentario(currentChar)) {
                        term +=currentChar;
                        term +=nextChar();
                        String ajudinha;
                        char quebralinha;

                        if (term.equals("@@")){

                            quebralinha=nextChar();

                            while (quebralinha != '\n'){
                                quebralinha=nextChar();
                                if (quebralinha=='\r'){
                                    contLine++;
                                }
                            }
                            contLine++;
                            ajudinha= Character.toString(quebralinha)+nextChar();
                            back();

                            if (ajudinha.equals("@@")){
                                term="";
                                nextChar();
                                nextChar();
                                return token;
                            }
                            else {
                                throw new LexicalException("Comentário em bloco não finalizado");
                            }
                        }

                        else {
                            quebralinha=nextChar();
                            while (quebralinha != '\r'){
                                quebralinha=nextChar();
                            }
                            nextChar();
                            term="";
                            contLine++;
                            return token;
                        }
                    }

                    else if (isEndLine(currentChar)){
                        term +=currentChar;

                        token.setType(Token.TK_PONTOFINAL);
                        token.setText(term);
                        token.setLine(contLine);
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

    private boolean isBarraR(char c){
        return c=='\r';
    }

    private boolean isBarraN(char c){
        return c=='\n';
    }

    private boolean isValorString(char c){
        return c=='#';
    }

    private boolean isValorChar(char c){
        return c=='&';
    }

    private boolean isLiteral(char c){
        return c=='"';
    }

    private boolean isComentario(char c){
        return c=='@';
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
