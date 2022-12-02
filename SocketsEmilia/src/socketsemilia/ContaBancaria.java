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

/**
 *
 * @author milif
 */
public class ContaBancaria extends Thread {

    public static void main(String args[]) {
        
        //Inicializa o ServerSocket da agencia
        ServerSocket conta = null;
        try {
            conta = new ServerSocket(40000);
            System.out.println("---------------------------------------");
            System.out.println("BEM VINDO AO BANCO PAGUE BEM!          |");
            System.out.println("Aguardadndo cliente...                 |");
            System.out.println("---------------------------------------");
            while (true) {
                
                //Quando o cliente se conecta, a thread inicializa da conta bancaria.
                Socket conexao = conta.accept();
                System.out.println("Cliente se conectou: " + conexao.getInetAddress().getHostAddress());
                Thread t = new ContaBancaria(conexao);
                t.start();

            }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
    
    //Socket de conexao da agencia bancaria
    private Socket conexao;
    public ContaBancaria(Socket conta) {
        conexao = conta;
    }

    //Inicializa o processo de comunicação das threads e do socket com o cliente
    //Onde pega a entrada de dados e mostra no servidor a saida do que o cliente digitou
    public void run() {

        try {
            while(true){
            
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            String clientedigitou = entrada.readUTF();
            System.out.println("Cliente Digitou: " + clientedigitou);
            saida.writeUTF(clientedigitou);
            saida.flush();
        }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }

    }
}
