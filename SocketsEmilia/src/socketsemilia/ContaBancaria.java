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
    private double saldo;
    AgenciaBancaria agencia = new AgenciaBancaria();
    Scanner entrada = new Scanner(System.in);

    
    public void extrato(String conta){
        System.out.println("\tEXTRATO");
        System.out.println("Saldo atual: " + this.getSaldo());
        System.out.println("Número da conta: " + conta);
    }
    
    public void sacar(double valor){
        if(saldo >= valor){
            this.setSaldo(this.getSaldo() - valor);
            System.out.println("Sacado R$ : " + valor);
            System.out.println("Novo saldo R$ : " + this.getSaldo() + "\n");
        } else {
            System.out.println("Saldo insuficiente. Faça um depósito\n");
        }
    }
    
    public void depositar(double valor){
        this.setSaldo(this.getSaldo() + valor);
        System.out.println("Depositado R$ : " + valor);
        System.out.println("Novo saldo R$ : " + this.getSaldo() + "\n");
    }
    
    public void criaConta(String conta){
        agencia.contas.add(conta);
        for (int i = 0; i < agencia.contas.size(); i++) {
            System.out.println("Conta nº " + agencia.contas.get(i));
            
        }
       
    }
    
    public void atualizarConta(String conta){ 
        System.out.println("Conta atualizada: " + conta);  
    }
     
    public void ConsultarConta(String conta){
        for (int i = 0; i < agencia.contas.size(); i++) {
            System.out.println("Conta nº " + agencia.contas.get(i));
            
        }  
        System.out.println("Saldo atual da conta: " + this.getSaldo());
           
    }
      
    public void deletarConta(String conta){
        System.out.println("Conta deletada: " + conta);
               
    }     
    
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
