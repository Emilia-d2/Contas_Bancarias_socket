/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

/**
 *
 * @author milif
 */
public class ServerDoBanco extends Thread {
    //Inicializa agencia e conta para fazer conexoes
    AgenciaBancaria agencia = new AgenciaBancaria();
    ContaBancaria contaBancaria = new ContaBancaria();

    public static void main(String args[]) {
        
        //Inicializa o ServerSocket da agencia
        ServerSocket server = null;
        try {
            server = new ServerSocket(40001);
            System.out.println("---------------------------------------");
            System.out.println("BEM VINDO AO BANCO PAGUE BEM!          |");
            System.out.println("Aguardadndo alguém se conectar...      |");
            System.out.println("---------------------------------------");
            while (true) {
                
                //Quando o gerente ou o cliente se conecta, a thread inicializa.
                Socket conexao = server.accept();
                System.out.println("Alguém se conectou: " + conexao.getInetAddress().getHostAddress());
                Thread t = new ServerDoBanco(conexao);
                t.start();

            }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
    
    //Socket de conexao da agencia bancaria
    private Socket conexao;
    public ServerDoBanco(Socket server) {
        conexao = server;
    }
    
    //Inicializa o processo de comunicação das threads e do socket com o gerente ou cliente
    //Onde pega a entrada de dados e mostra no servidor a saida do que o gerente ou o cliente digitou
    public void run() {

        try {
            while(true){
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            String digitou = entrada.readUTF();
            System.out.println("Alguém Digitou: " + digitou);
            
            //Parametros de cliente
            //Parametro 1 operacao
            //Parametro 2 agencia
            //Parametro 3 conta 
            //Parametro 4 nome
            //Parametro 5 cpf 
            //Parametro 6 valor
            String[] vet = digitou.split(";");
            String param1 = vet[0];
            String param2 = vet[1];
            String param3 = vet[2];
            String param4 = vet[3];
            String param5 = vet[4];
            String param6 = vet[5];
            
            //Lógica para verificar qual operação vai ser
            /* 1-Depositar | 2-Sacar | 3-extrato | 4-Criar Conta
               5-Consultar conta | 6-atualizar Conta | 7-Deletar Conta
            */
            if("1".equals(param1)){
                saida.writeUTF("Deposito realizado com sucesso!");
                contaBancaria.depositar(Double.parseDouble(param6), param3);
               
            }
            if("2".equals(param1)){
                saida.writeUTF("Saque realizado com sucesso!");
                contaBancaria.sacar(Double.parseDouble(param6), param3);
                
            }
            if("3".equals(param1)){
                saida.writeUTF("Extrato emitido!");
                contaBancaria.extrato(param3);            
            }
            if("4".equals(param1)){
                saida.writeUTF("Conta criada com sucesso!");
                contaBancaria.criaConta(param3);               
            }
            if("5".equals(param1)){
                saida.writeUTF("Conta consultada!");
                contaBancaria.ConsultarConta(param3); 
            }
            if("6".equals(param1)){
                saida.writeUTF("A conta foi atualizada!");
                contaBancaria.atualizarConta(param3);  
            }
            if("7".equals(param1)){
                saida.writeUTF("A conta foi deletada com sucesso!");
                contaBancaria.deletarConta(param3);
                
            }
            
        }

        } catch (IOException e) {
            System.out.println("IOException " + e);
        }

    }
}