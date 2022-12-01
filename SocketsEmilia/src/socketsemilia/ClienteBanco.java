/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author milif
 */
public class ClienteBanco extends Thread {
    private Scanner entradaDados;
    private String agencia;
    private String conta;
    private String nome;         
    private String cpf;
    private float deposito;
    private float saque;
    private float valorTotal;
    DataOutputStream saida;
    BufferedReader teclado;
    BufferedReader entrada;

    public static void main(String args[]) {

        try {

            Socket conexao = new Socket("localhost", 40000);
            System.out.println("Conectado em minha conta bancária... ");
            Thread t = new ClienteBanco(conexao);
            t.start();

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }
    private Socket conexao;

    public ClienteBanco(Socket conta) {
        conexao = conta;
    }

    ClienteBanco() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public void run() {
        try {
            saida = new DataOutputStream(conexao.getOutputStream());
            teclado = new BufferedReader(new InputStreamReader(System.in));
            
            int opcao = 1;
            while(opcao != 0) {
                this.entradaDados = new Scanner(System.in);
                System.out.println("--------------------------");
                System.out.println(" 0 - Sair                 |");
                System.out.println(" 1 - Deposito             |");
                System.out.println(" 2 - Saque                |");
                System.out.println(" 3 - Consulta extrato     |");
                System.out.println("--------------------------");
                System.out.println("Digite a opção: ");
                opcao = this.entradaDados.nextInt();
                
               if(opcao == 1){
                    deposito();
                    System.out.println("Depósito realizado com sucesso!");
                   
               }
               if(opcao == 2){
                   saque();
                   System.out.println("Saque realizado com sucesso!");
                   
               }
               if(opcao == 3){
                   extrato();
                   
               }
                if(opcao == 0){
                   
                   break;
               }
                String digito = mensagem("226", conta, nome, cpf);
                saida.writeUTF(digito);
                saida.flush();

                entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                entrada.readLine();
            }
            
           
           
        } catch (IOException e) {
            System.out.println("IOException" + e);
        } finally {
            try {
                conexao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    public void deposito() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor para deposito: ");
            this.deposito = this.entradaDados.nextFloat();
            valorTotal = deposito;
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saque() {
        try {
           this.entradaDados = new Scanner(System.in);
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor que deseja Sacar: ");
            this.saque = this.entradaDados.nextFloat();
            valorTotal = valorTotal - saque;
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrato() {
        try {
           this.entradaDados = new Scanner(System.in);
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
            System.out.println("Seu saldo é: " + valorTotal);
          

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String mensagem(String agencia, String conta, String nome, String cpf){
        return "\nAgência: " + this.agencia + "; \n" + "N° conta: " + this.conta + "; \n" + "Nome CLiente: " + this.nome + "; \n" + "CPF: " + this.cpf + ";\n";
    }
}
