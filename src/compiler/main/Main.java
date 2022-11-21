package compiler.main;

import compiler.exceptions.LexicalException;
import compiler.lexico.Scanner;
import compiler.lexico.Token;
import compiler.parsing.Parsing;

import java.util.ArrayList;
import java.util.Collections;
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
                    if (token.getType() != 0){
                        listaTokens.add(token.getType());
                    }
                }

            } while (token != null);

            List<Integer> analiseSintaticaLista = new ArrayList<>();
            analiseSintaticaLista.add(51);
            Integer itemAnalise = analiseSintaticaLista.get(0);

            System.out.println("\n\n"+listaTokens);
            Parsing parsing = new Parsing();
            Integer contlinha = 1;
            Integer nextToken =0;

            while (itemAnalise != 47){

                //Terminais
                if (itemAnalise >= 1 && itemAnalise <= 50){
                    int tkTipo = listaTokens.get(nextToken);

                    if (itemAnalise ==  17){
                        analiseSintaticaLista.remove(analiseSintaticaLista.size()-1);
                        itemAnalise = analiseSintaticaLista.get(analiseSintaticaLista.size()-1);
                        imprimirPilha(analiseSintaticaLista);
                    } else if (itemAnalise == tkTipo) {

                        if (itemAnalise == 40){
                            contlinha++;
                        }

                        analiseSintaticaLista.remove(analiseSintaticaLista.size()-1);

                        if (analiseSintaticaLista.size() == 0){
                            itemAnalise = 47;
                        } else {
                            itemAnalise = analiseSintaticaLista.get(analiseSintaticaLista.size()-1);
                        }
                        imprimirPilha(analiseSintaticaLista);
                        nextToken++;
                    } else {
                        System.out.println("Erro de análise Sintática na linha:" +contlinha);
                        System.exit(0);
                    }

                }

                //Não terminais
                else if(itemAnalise >= 51 && itemAnalise <=82){
                    int terminal = listaTokens.get(nextToken);
                    int naoTerminal = itemAnalise;

                    try {
                          var producao = parsing.tabParsing[naoTerminal][terminal];
                          var producaoPilha = parsing.producoes[producao-1];
                          ArrayList<Integer> producaoPilhaInversa = new ArrayList<Integer>();

                          for(var i = 0; i<producaoPilha.length; i++){
                            if(producaoPilha[i] != 0)
                                producaoPilhaInversa.add(producaoPilha[i]);
                        }

                        Collections.reverse(producaoPilhaInversa);
                        analiseSintaticaLista.remove(analiseSintaticaLista.size()-1);
                        for (int p : producaoPilhaInversa) {
                            analiseSintaticaLista.add(p);
                        }
                        itemAnalise = analiseSintaticaLista.get(analiseSintaticaLista.size()-1);
                        imprimirPilha(analiseSintaticaLista);

                    }catch (Exception e){
                        System.out.println("Erro de análise Sintática na linha:" +contlinha);
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Erro de análise Sintática!");
                    System.exit(0);
                }
            }
            System.out.println("Análise Sintática realizada com Sucesso");
            System.exit(0);

        } catch (LexicalException ex){
            System.out.println("\n[Lexical ERROR] "+ex.getMessage());
        }

        catch (Exception ex){
            System.out.println("\n[Generic ERROR] "+ex.getMessage());
        }
    }

    public static void imprimirPilha(List<Integer> pilha)
    {
        int cont = 1;
        System.out.println("Pilha: {");
        for (int p : pilha)
        {
            if(cont != pilha.size())
                System.out.println(p + ", ");
            else
                System.out.println(p);

            cont++;
        }
        System.out.println("}");
        System.out.println();
    }

}
