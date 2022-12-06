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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author milif
 */

public class GerenteAgencia extends Thread {
    //Variaveis da classe
    private Scanner entradaDados;
    public String conta;
    AgenciaBancaria agencia = new AgenciaBancaria();
    ContaBancaria contaBancaria = new ContaBancaria();
    public String descricao;
    DataOutputStream saida;
    BufferedReader teclado;
    BufferedReader entrada;
    private String tipoOperacao;

    public static void main(String args[]) {

        try {
            //Inicializa a conexão e inicaliza a thread.
            Socket conexao = new Socket("localhost", 40001);
            System.out.println("Conectado na Agência... ");
            Thread t = new GerenteAgencia(conexao);
            t.start();

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }
    
    //Socket de conexao do gerente bancario
    private Socket conexao;
    public GerenteAgencia(Socket agencia) {
        conexao = agencia;
    }

    public void run() {
        try {
            //Inicializa o programa do gerente, sendo ele um menu 
            saida = new DataOutputStream(conexao.getOutputStream());
            teclado = new BufferedReader(new InputStreamReader(System.in));
            
            int opcao = 1;
            while(opcao != 0) {
                this.entradaDados = new Scanner(System.in);
                System.out.println("---------------------------");
                System.out.println(" 0 - Sair                  |");
                System.out.println(" 1 - Nova Conta            |");
                System.out.println(" 2 - Consulta Conta        |");
                System.out.println(" 3 - Atualizar Conta       |");
                System.out.println(" 4 - Deletar Conta         |");
                System.out.println("---------------------------");
                System.out.println("Digite a opção ==> ");
                opcao = this.entradaDados.nextInt();
                
               if(opcao == 1){
                    criaConta();
               }
                if(opcao == 2){
                   consultaConta();
               }
               if(opcao == 3){
                   atualizaConta();
               }
               if(opcao == 4){
                   deletarConta();
               }
               if(opcao == 0){
               break;
               }
               
               //A partir da escolha do gerente irá retornar a mensagem com os dados 
               //Essa mensagem vai ir para o server, no caso fazendo a comunicação
                String digito = mensagem(tipoOperacao, this.getConta(), descricao);
                saida.writeUTF(digito);
                saida.flush();

                //Dados de entrada de dados do server, onde ele retorna se deu certo a operação
                // ou se não deu certo, fazendo a comunicação.
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
    
    //Função de Criar conta bancária
    public void criaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "4";
            System.out.println("Informe o n° da conta bancaria que deseja criar: ");
            this.conta = this.entradaDados.next();
            this.setConta(conta);
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.getConta(), this.descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Função de consultar conta bancária
    public void consultaConta() {
        try {
           this.entradaDados = new Scanner(System.in);
           this.tipoOperacao = "5";
            System.out.println("Informe o n° da conta bancaria que deseja consultar: ");
            this.conta = this.entradaDados.next();
            this.setConta(conta);
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.getConta(), this.descricao);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Função de atualizar conta bancária
    public void atualizaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "6";
            System.out.println("Informe o n° da conta bancaria que deseja atualizar: ");
            this.conta = this.entradaDados.next();
            this.setConta(conta);
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.getConta(), this.descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Função de deletar conta bancária
    public void deletarConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "7";
            System.out.println("Informe o n° da conta bancaria que deseja deletar: ");
            this.conta = this.entradaDados.next();
            this.setConta(conta);
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
           mensagem(this.tipoOperacao, this.getConta(), this.descricao);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Mensagem do protocolo que vai retornar para o server.
    public String mensagem(String operacao, String conta, String descricao){
        return this.tipoOperacao + ";" + "0226" + ";" + this.getConta() + ";" + this.descricao + ";" + "000" + ";" + contaBancaria.getSaldo() + "\n";
    }

    
    //Getters e Setters.
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        agencia.contas.add(conta);
    }
    
    
    
}
