/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.util.Scanner;



/**
 *
 * @author milif
 */

public class ContaBancaria{
    //Variáveis da conta bancária
    private double saldo;
    AgenciaBancaria agencia = new AgenciaBancaria();
    Scanner entrada = new Scanner(System.in);

    //Função para exibir extrato no server
    public void extrato(String conta){
        System.out.println("\tEXTRATO");
        System.out.println("Saldo atual: " + this.getSaldo());
        System.out.println("Número da conta: " + conta);
    }
    
    //Função para sacar e mostrar no server
    public void sacar(double valor, String conta){
        if(saldo >= valor){
            this.setSaldo(this.getSaldo() - valor);
            System.out.println("Conta n°: " + conta);
            System.out.println("Sacado R$ : " + valor);
            System.out.println("Novo saldo R$ : " + this.getSaldo() + "\n");
        } else {
            System.out.println("Saldo insuficiente. Faça um depósito\n");
        }
    }
    
    //Função para depositar e mostrar no server
    public void depositar(double valor, String conta){
        this.setSaldo(this.getSaldo() + valor);
        System.out.println("Conta n°: " + conta);
        System.out.println("Depositado R$ : " + valor);
        System.out.println("Novo saldo R$ : " + this.getSaldo() + "\n");
    }
    
    //Função de criar conta e mostrar no server
    public void criaConta(String conta){
        agencia.contas.add(conta);
        for (int i = 0; i < agencia.contas.size(); i++) {
            System.out.println("Conta criada de nº " + agencia.contas.get(i));
            
        }
       
    }
    
    //Função para atualizar conta e mostrar no server
    public void atualizarConta(String conta){ 
        System.out.println("Conta atualizada: " + conta);  
    }
     
    //Função para consultar conta e mostrar no server
    public void ConsultarConta(String conta){
        if(conta == null ){
            System.out.println("Você ainda não criou uma conta");
        }
        else{
            for (int i = 0; i < agencia.contas.size(); i++) {
                System.out.println("Conta consultada de nº " + agencia.contas.get(i));
                System.out.println("Saldo atual da conta: " + this.getSaldo());

            }  
        }
         
    }
      
    //Função para deletar conta e mostrar no server
    public void deletarConta(String conta){
        agencia.contas.remove(conta);
        System.out.println("Conta deletada: " + conta);
               
    }     
    
    //Getters e setters. 
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Scanner getEntrada() {
        return entrada;
    }

    public void setEntrada(Scanner entrada) {
        this.entrada = entrada;
    }
    
    
    
}
