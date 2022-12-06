/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.io.BufferedReader;
import java.io.DataInputStream;
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
    //Variaveis da classe
    private Scanner entradaDados;
    private String agencia;
    private String conta;
    private String nome;  
    private String cpf;
    private float valor;
    private String tipoOperacao;
    DataOutputStream saida;
    BufferedReader teclado;
    BufferedReader entrada;

    public static void main(String args[]) {

        try {
            //Inicializa a conexão e inicaliza a thread.
            Socket conexao = new Socket("localhost", 40001);
            System.out.println("Conectado em minha conta bancária... ");
            Thread t = new ClienteBanco(conexao);
            t.start();

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }
    
    //Socket de conexao do cliente
    private Socket conexao;
    public ClienteBanco(Socket conta) {
        conexao = conta;
    }



    public void run() {
        //Inicializa o programa do cliente, sendo ele um menu 
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
                    
                   
               }
               if(opcao == 2){
                   saque();
                   
                   
               }
               if(opcao == 3){
                   extrato();
                   
               }
                if(opcao == 0){
                   
                   break;
               }
                //A partir da escolha do cliente irá retornar a mensagem com os dados 
                //Essa mensagem vai ir para a conta bancaria, no caso fazendo a comunicação
                String digito = mensagem(tipoOperacao, agencia, conta, nome, cpf, valor);
                saida.writeUTF(digito);
                saida.flush();
                
               
                DataInputStream entradaRetorno = new DataInputStream(conexao.getInputStream());

                String resposta = entradaRetorno.readUTF();
                System.out.println(resposta);
                
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
    
    
    //Funções
    public void deposito() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "1";
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor para deposito: ");
            this.valor = this.entradaDados.nextFloat();
            mensagem(this.tipoOperacao, "0226", this.conta, this.nome, this.cpf, this.valor);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saque() {
        try {
           this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "2";
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            System.out.println("Informe o valor que deseja Sacar: ");
            this.valor = this.entradaDados.nextFloat();
            mensagem(this.tipoOperacao, "0226", this.conta, this.nome, this.cpf, this.valor);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrato() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "3";
            System.out.println("Informe a conta bancaria: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe seu nome: ");
            this.nome = this.entradaDados.next();
            System.out.println("Informe seu CPF: ");
            this.cpf = this.entradaDados.next();
            mensagem(this.tipoOperacao, "0226", this.conta, this.nome, this.cpf, this.valor);
          

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Mensagem de protocolo da conta bancaria
    public String mensagem(String operacao, String agencia, String conta, String nome, String cpf, float valor){
        return this.tipoOperacao + ";" + "0226" + ";"  + this.conta + ";" + this.nome + ";" + this.cpf + ";" + this.valor + "\n";
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
