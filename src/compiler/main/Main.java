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
    private static List<String> listaTextTokens = new ArrayList();

    private static List<String> listaSimbolos = new ArrayList();

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner("file4");
            Token token = null;
            do {
                token = sc.nextToken();
                if(token != null){
                    System.out.println(token);
                    if (token.getType() != 0){
                        listaTokens.add(token.getType());
                        listaTextTokens.add(token.getText());
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

            //inicio da análise sintatica
            while (itemAnalise != 47){

                //Terminais
                if (itemAnalise >= 1 && itemAnalise <= 50){
                    int tkTipo = listaTokens.get(nextToken);

                    if (itemAnalise ==  17){
                        analiseSintaticaLista.remove(analiseSintaticaLista.size()-1);
                        itemAnalise = analiseSintaticaLista.get(analiseSintaticaLista.size()-1);
                        imprimirPilha(analiseSintaticaLista);
                    } else if (itemAnalise == tkTipo) {

                        analiseSintaticaLista.remove(analiseSintaticaLista.size()-1);

                        if (analiseSintaticaLista.size() == 0){
                            itemAnalise = 47;
                        } else {
                            itemAnalise = analiseSintaticaLista.get(analiseSintaticaLista.size()-1);
                        }
                        imprimirPilha(analiseSintaticaLista);
                        nextToken++;
                    } else {
                        System.out.println("Erro de análise Sintática");
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
                        System.out.println("Erro de análise Sintática");
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Erro de análise Sintática!");
                    System.exit(0);
                }
            }
            System.out.println("Análise Sintática realizada com Sucesso");
            System.out.println("\nLista dos tokens: "+listaTokens);
            System.out.println("Texto dos tokens: "+listaTextTokens);

            //inicio da análise semantica
            for(int z = 0; z<= listaTokens.size(); z++){

              try {
                  if(listaTokens.get(z).equals(7)){

                      int tipoVar = listaTokens.get(z+2);

                      //verifica se o token anterior a variavél não possui declaração string int integer
                      if(tipoVar != 3 && tipoVar != 13 && tipoVar != 14){

                          //se entrar no if como o token anterior não tem declaração, vai verificar se a variavel já está na tabela de simbolos.
                          if(!listaSimbolos.contains(listaTextTokens.get(z))){
                              System.out.println("\nErro Semantico: A variavel: "+ listaTextTokens.get(z)+" Não foi inicializada");
                              System.exit(0);
                          }else {

                              //pegando o nome da minha variavel atual
                              String verificarToken = listaTextTokens.get(z);

                              //percorrendo minha lista de simbolos para verificar o nome da minha variavel
                              for (int w = 0; w <= listaSimbolos.size(); w++)
                              {

                                  //quando encontrar o nome da minha variavel vou pegar a posição do nome-1 que irá retornar o tipo dela
                                  if (listaSimbolos.get(w).equals(verificarToken))
                                  {
                                      verificarToken = listaSimbolos.get(w-1);

                                      //com o tipo da minha variavél atual, é possível iniciar processo de validação de acordo com o tipo dela

                                      // verifico se minha variavel atual é string
                                      if(verificarToken.equals("string"))
                                      {

                                          //verificar se estou atribuindo a uma outra variavel e verifico o tipo dela
                                          if(listaTokens.get(z+2) == 7 && listaTokens.get(z+1) == 32)
                                          {
                                              //se for uma variavel vou pegar a possição dessa variavel e pegar o nome dela
                                              String verificarTipoVarAtribuida = listaTextTokens.get(z+2);

                                              //percorrer minha lista de simbolos e verificar se o nome dela contém na lista.
                                              for(int y = 0; y <=listaSimbolos.size(); y++)
                                              {
                                                  //verificando se o nome está presente na lista
                                                  if(listaSimbolos.get(y).equals(verificarTipoVarAtribuida))
                                                  {
                                                      //quando encontrar o nome eu pego a posição do nome-1 para pegar o tipo de variavel que ela pertence
                                                      verificarTipoVarAtribuida = listaSimbolos.get(y-1);

                                                      if (!verificarTipoVarAtribuida.equals("string")){
                                                          System.out.println("\nErro Semantico: Não é possível atribuir um inteiro a uma variavel string!");
                                                          System.exit(0);
                                                      }

                                                  }
                                              }



                                          }

                                          //verifico se ela está recebendo um número
                                          else if (listaTokens.get(z+2) == 5 && listaTokens.get(z+1) == 32)
                                          {
                                              System.out.println("\nErro Semantico: Não é possível relacionar um inteiro a uma variavel string!");
                                              System.exit(0);
                                          }

                                          //verifico se ela está fazendo operações aritimeticas
                                          else {
                                              System.out.println("\nErro Semantico: Não é possível fazer essa relação com uma variavel string!");
                                              System.exit(0);
                                          }

                                          // ou se minha variavel é numerica interger ou int
                                      }else if (verificarToken.equals("integer") || verificarToken.equals("int")) {

                                          //verificar se estou atribuindo a uma outra variavel e verifico o tipo dela
                                          if (listaTokens.get(z + 2) == 7 && (listaTokens.get(z+1) == 30 || listaTokens.get(z+1) == 35 || listaTokens.get(z+1) == 37)
                                                  || listaTokens.get(z+1) == 32) {
                                              //se for uma variavel vou pegar a possição dessa variavel e pegar o nome dela
                                              String verificarTipoVarAtribuida = listaTextTokens.get(z + 2);

                                              //percorrer minha lista de simbolos e verificar se o nome dela contém na lista.
                                              for (int y = 0; y <= listaSimbolos.size(); y++) {
                                                  //verificando se o nome está presente na lista
                                                  if (listaSimbolos.get(y).equals(verificarTipoVarAtribuida)) {
                                                      //quando encontrar o nome eu pego a posição do nome-1 para pegar o tipo de variavel que ela pertence
                                                      verificarTipoVarAtribuida = listaSimbolos.get(y - 1);

                                                      if (verificarTipoVarAtribuida.equals("string")) {
                                                          System.out.println("\nErro Semantico: Não é possível atribuir uma string a uma variavel numerica!");
                                                          System.exit(0);
                                                      }

                                                  }
                                              }


                                          } else if ((listaTokens.get(z+1) == 30 || listaTokens.get(z+1) == 35 || listaTokens.get(z+1) == 37
                                                  || listaTokens.get(z+1) == 32) && listaTokens.get(z+2) != 5)
                                          {
                                              System.out.println("\nErro Semantico: Variavel do tipo numerica só aceita numeros");
                                              System.exit(0);
                                          }
                                      }
                                  }
                              }
                          }

                      }
                      // se possuir string int ou integer anterior ele já adiciona a variavel na lista de simbolos.
                      else if (tipoVar == 3){

                          if (!listaSimbolos.contains(listaTextTokens.get(z))){
                              listaSimbolos.add("string");
                              listaSimbolos.add(listaTextTokens.get(z));
                              System.out.println(listaSimbolos);
                          }else {
                              System.out.println("\nErro Semantico: A variavel "+listaTextTokens.get(z)+" já foi declarada");
                              System.exit(0);
                          }

                      }
                      else if(tipoVar == 13){

                          if (!listaSimbolos.contains(listaTextTokens.get(z))){
                              listaSimbolos.add("integer");
                              listaSimbolos.add(listaTextTokens.get(z));
                              System.out.println(listaSimbolos);
                          }else {
                              System.out.println("\nErro Semantico: A variavel "+listaTextTokens.get(z)+" já foi declarada");
                              System.exit(0);
                          }
                      }
                      else if(tipoVar == 14) {

                          if (!listaSimbolos.contains(listaTextTokens.get(z))){
                              listaSimbolos.add("int");
                              listaSimbolos.add(listaTextTokens.get(z));
                              System.out.println(listaSimbolos);
                          }else {
                              System.out.println("\nError: A variavel "+listaTextTokens.get(z)+" já foi declarada");
                              System.exit(0);
                          }
                      }
                  }
              }catch (Exception e){}

            }
            //fim do for

            System.out.println("\nFim análise semantica");
            String codigoVisualG = "";

            for(int i = 0; i<listaTokens.size(); i++){

                switch (listaTokens.get(i)) {

                    case 1:
                        break;

                    case 2:
                        codigoVisualG += "algoritmo ";
                        break;

                    case 3:
                        codigoVisualG += "caractere "+'\n';
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                    case 7:
                        if (!codigoVisualG.contains("para")){
                            codigoVisualG += ""+listaTextTokens.get(i);
                        }
                        break;

                    case 8:
                        break;

                    case 9:
                        break;

                    case 10:
                        break;

                    case 11:
                        codigoVisualG +='"'+"meuprograma"+'"'+' ';
                        break;

                    case 12:
                        break;

                    case 13:
                        codigoVisualG += "inteiro "+'\n';
                        break;

                    case 14:
                        break;

                    case 15:
                        codigoVisualG += "Inicio "+'\n';
                        break;

                    case 16:
                        break;

                    case 17:
                        break;

                    case 18:
                        codigoVisualG +="para "+listaTextTokens.get(i+2)+" de "+listaTextTokens.get(i+4)+" ate "+listaTextTokens.get(i+8)+ " faca";
                        break;

                    case 19:
                        break;

                    case 20:
                        codigoVisualG += '\n'+"fimpara"+'\n'+"fimalgoritmo";
                        break;

                    case 21:
                        break;

                    case 22:
                        break;

                    case 23:
                        break;

                    case 24:

                        codigoVisualG +='\n'+"escreval ("+listaTextTokens.get(i+2)+")";
                        break;

                    case 25:
                        break;

                    case 26:
                        break;

                    case 27:
                        break;

                    case 28:
                        break;

                    case 29:
                        break;

                    case 30:
                        break;

                    case 31:
                        break;

                    case 32:
                        break;

                    case 33:
                        break;

                    case 34:
                        break;

                    case 35:
                        break;

                    case 36:
                        break;

                    case 37:
                        break;

                    case 38:
                        break;

                    case 39:
                        if (!codigoVisualG.contains("var")){
                            codigoVisualG +='\n'+"var "+'\n';
                        }
                        break;

                    case 40:
                        break;


                    case 41:
                        codigoVisualG += ": ";
                        break;
                    case 50:
                        break;

                    case 51:
                        break;

                    case 52:
                        break;

                    case 53:
                        break;

                    case 54:
                        break;

                    case 55:
                        break;

                    case 56:
                        break;

                    case 57:
                        break;
                }
            }

            System.out.println("\n===Codigo gerado em portugol===\n"+codigoVisualG);
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
