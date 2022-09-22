package compiler.main;

import compiler.exceptions.LexicalException;
import compiler.lexico.Scanner;
import compiler.lexico.Token;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner("file");
            Token token = null;

            do {
                token = sc.nextToken();
                if(token != null){
                    System.out.println(token);
                }
            } while (token != null);

        } catch (LexicalException ex){
            System.out.println("Lexical ERROR "+ex.getMessage());
        }

        catch (Exception ex){
            System.out.println("Generic ERROR"+ex.getMessage());
        }
    }
}
