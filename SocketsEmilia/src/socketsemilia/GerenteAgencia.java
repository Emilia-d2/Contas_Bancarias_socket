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

public class GerenteAgencia extends Thread {
    
    private Scanner entradaDados;
    public String conta;
    public String descricao;
    DataOutputStream saida;
    BufferedReader teclado;
    BufferedReader entrada;

    public static void main(String args[]) {

        try {

            Socket conexao = new Socket("localhost", 40001);
            System.out.println("Conectado na Agência... ");
            Thread t = new GerenteAgencia(conexao);
            t.start();

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }
    private Socket conexao;

    public GerenteAgencia(Socket agencia) {
        conexao = agencia;
    }

    public void run() {
        try {
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
               String digito = mensagem(conta, descricao);
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
    

    public void criaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja criar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.conta, this.descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultaConta() {
        try {
           this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja consultar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.conta, this.descricao);
            
            String contaConsultada = this.conta;
            
            if(contaConsultada != null){
                System.out.println("A conta que você consultou é essa: " + contaConsultada);
            }else{
                System.out.println("Não foi possível encontrar a essa conta consultada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja atualizar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.conta, this.descricao);
        String contaAtualizar = this.conta;
        if(contaAtualizar != null){
            this.entradaDados = new Scanner(System.in);
            System.out.println("Atualize o n° da conta : ");
            String atualizaConta = this.entradaDados.next();
            System.out.println("Atualize a descrição: ");
            this.descricao = this.entradaDados.next();
            this.conta = atualizaConta;
            mensagem(this.conta, this.descricao);
        }else{
            System.out.println("Não foi possível atualizar sua conta! Tente novamente mais tarde");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletarConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja deletar: ");
            this.conta = this.entradaDados.next();
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
           mensagem(this.conta, this.descricao);
        String deletarConta = this.conta;
        if(deletarConta != null){
            this.conta = "";
            System.out.println("Informe a descrição: ");
            this.descricao = this.entradaDados.next();
            mensagem(this.conta, this.descricao);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String mensagem(String conta, String descricao){
        return "\nAgência 0226 - n° Conta: " + this.conta + "; \n" + "Descição: " + this.descricao + "; \n";
    }
}
