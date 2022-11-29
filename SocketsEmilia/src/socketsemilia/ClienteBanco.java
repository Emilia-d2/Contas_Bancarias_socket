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


    public void run() {
        try {
            saida = new DataOutputStream(conexao.getOutputStream());
            teclado = new BufferedReader(new InputStreamReader(System.in));
            
            
            
            int opcao;
            this.entradaDados = new Scanner(System.in);
            System.out.println("--------------------------");
            System.out.println(" 0 - Sair                 |");
            System.out.println(" 1 - Deposito             |");
            System.out.println(" 2 - Saque                |");
            System.out.println(" 3 - Consulta extrato     |");
            System.out.println("--------------------------");
            System.out.println("Digite a opção: ");
            opcao = this.entradaDados.nextInt();

//            String escolha;
//            Scanner ler = new Scanner(System.in);
//            System.out.println("Digite sim para continuar:");
//            escolha = ler.next();
            
            while(opcao != 0) {
                
               if(opcao == 1){
                    deposito();
                    break;
               }
               else if(opcao == 2){
                   deposito();
                   break;
               }
               else if(opcao == 3){
                   extrato();
                   break;
               }
               else{
               break;
               }
            }
            
           
            String digito = mensagem(agencia, conta, nome, cpf);
            saida.writeUTF(digito);
            saida.flush();

            entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            entrada.readLine();
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
            System.out.println("Informe sua agência bancária: ");
            this.agencia = this.entradaDados.next();
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor para deposito: ");
            this.deposito = this.entradaDados.nextFloat();
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saque() {
        try {
           this.entradaDados = new Scanner(System.in);
            System.out.println("Informe sua agência bancária: ");
            this.agencia = this.entradaDados.next();
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor para deposito: ");
            this.saque = this.entradaDados.nextFloat();
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrato() {
        try {
           this.entradaDados = new Scanner(System.in);
            System.out.println("Informe sua agência bancária: ");
            this.agencia = this.entradaDados.next();
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            mensagem(this.agencia, this.conta, this.nome, this.cpf);
          

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String mensagem(String agencia, String conta, String nome, String cpf){
        return "\n Agência: " + this.agencia + "; \n" + "N° conta: " + this.conta + "; \n" + "Nome CLiente: " + this.nome + "; \n" + "CPF: " + this.cpf + ";";
    }
}
