package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {

    public static void main(String[] args){

        StringBuilder sb = new StringBuilder();
        // caminho do artigo
        Path path = Paths.get("Calc1.stk");

        String line;
        Stack stack = new Stack();
        int firstNum;
        int secndNum;

        try (BufferedReader br = Files.newBufferedReader(path)) {

            // lendo linha por linha do arquivo
            while ((line = br.readLine()) != null) {
                if (isInt(line)){
                    stack.push(line);
                    // se for número, adicionamos na pilha para fazer as contas quando encontrarmos um sinal de operação
                }
                else {
                    // encontramos um sinal de operação
                    secndNum = Integer.parseInt((String) stack.pop()); // segundo operando
                    firstNum = Integer.parseInt((String) stack.pop()); // primeiro operando

                    // realizará a conta entre secndNum e firstNum de acordo com o sinal que leu
                    if (line.equals("*")){
                        stack.push((firstNum*secndNum)+"");
                    }
                    else if (line.equals("+")){
                        stack.push((firstNum+secndNum)+"");
                    }
                    else if (line.equals("-")){
                        stack.push((firstNum-secndNum)+"");
                    }
                    else if (line.equals("/")){
                        stack.push((firstNum/secndNum)+"");
                    }
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // ao fim de tudo, o único elemento na pilha será o resultado final
        System.out.println(stack.pop());

    }

    public static boolean isInt(String line){
        // utilizada para receber uma linha do arquivo e dizer se é um número ou não (após as devidas conversões)
        try{
            Integer.parseInt(line);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }

}