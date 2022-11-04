package compiler.main;

import compiler.exceptions.LexicalException;
import compiler.lexico.Scanner;
import compiler.lexico.Token;
import compiler.parsing.Parsing;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> listaTokens = new ArrayList();

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner("file");
            Token token = null;
            do {
                token = sc.nextToken();
                if(token != null){
                    System.out.println(token);
                    listaTokens.add(token.getType());
                }
            } while (token != null);

            Parsing parsing = new Parsing();
            System.out.println("\n\n"+listaTokens);
            listaTokens.add(47);
            Integer aux = listaTokens.get(0);

            while(aux != 47){
                if (aux >= 1 && aux <= 50){
                }
            }

        } catch (LexicalException ex){
            System.out.println("\n[Lexical ERROR] "+ex.getMessage());
        }

        catch (Exception ex){
            System.out.println("\n[Generic ERROR] "+ex.getMessage());
        }
    }
}
