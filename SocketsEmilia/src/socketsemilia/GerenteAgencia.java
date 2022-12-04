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
               //Essa mensagem vai ir para a agencia bancaria, no caso fazendo a comunicação
                String digito = mensagem(tipoOperacao, conta, descricao);
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
    public void criaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "4";
            System.out.println("Informe o n° da conta bancaria que deseja criar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.conta, this.descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultaConta() {
        try {
           this.entradaDados = new Scanner(System.in);
           this.tipoOperacao = "5";
            System.out.println("Informe o n° da conta bancaria que deseja consultar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.conta, this.descricao);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "6";
            System.out.println("Informe o n° da conta bancaria que deseja atualizar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.tipoOperacao, this.conta, this.descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletarConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            this.tipoOperacao = "7";
            System.out.println("Informe o n° da conta bancaria que deseja deletar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
           mensagem(this.tipoOperacao, this.conta, this.descricao);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Mensagem do protocolo que vai retornar para a agencia bancaria
    public String mensagem(String operacao, String conta, String descricao){
        return this.tipoOperacao + ";" + "0226" + ";" +this.conta + ";" + this.descricao + ";" + "000" + ";" + 00.00 +"\n";
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
