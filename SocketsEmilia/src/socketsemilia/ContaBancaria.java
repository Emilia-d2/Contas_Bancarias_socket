/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.util.List;
import java.util.Scanner;



/**
 *
 * @author milif
 */
public class ContaBancaria{
    private String nome;
    private List<ContaBancaria> conta;
    private double saldo;
    AgenciaBancaria agencia = new AgenciaBancaria();
    Scanner entrada = new Scanner(System.in);

    
    public void extrato(String conta){
        System.out.println("\tEXTRATO");
        System.out.printf("Saldo atual: %.2f\n",this.saldo);
       
        
    }
    
    public void sacar(double valor){
        if(saldo >= valor){
            saldo -= valor;
            System.out.println("Sacado R$ : " + valor);
            System.out.println("Novo saldo R$ : " + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente. Faça um depósito\n");
        }
    }
    
    public void depositar(double valor)
    {
        saldo += valor;
        System.out.println("Depositado R$ : " + valor);
        System.out.println("Novo saldo R$ : " + saldo + "\n");
    }
    
    public void criaConta(String conta){
        agencia.setContas(this.conta);
        System.out.println("Conta criada: " + agencia.getContas());
    }
    
    public void atualizarConta(String conta){
        agencia.setContas(this.conta);
        System.out.println("Conta atualizada: " + this.conta);  
    }
     
    public void ConsultarConta(String conta){
        List<ContaBancaria> pegaConta = agencia.getContas();
        System.out.println("Conta consultada: " + pegaConta);  
           
    }
      
    public void deletarConta(String conta){
        System.out.println("Conta deletada: " + this.conta);
               
    }
       
    
    public String getNome() {
        return nome;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
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
