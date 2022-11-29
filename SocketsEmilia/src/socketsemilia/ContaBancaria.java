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

        ServerSocket conta = null;
        try {
            conta = new ServerSocket(40000);
            System.out.println("---------------------------------------");
            System.out.println("BEM VINDO AO BANCO PAGUE BEM!          |");
            System.out.println("Aguardadndo cliente...                 |");
            System.out.println("---------------------------------------");
            while (true) {

                Socket conexao = conta.accept();
                System.out.println("Cliente se conectou: " + conexao.getInetAddress().getHostAddress());
                Thread t = new ContaBancaria(conexao);
                t.start();

            }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
    private Socket conexao;

    public ContaBancaria(Socket conta) {
        conexao = conta;
    }

    public void run() {

        try {

            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            String clientedigitou = entrada.readUTF();
            System.out.println("Cliente Digitou: " + clientedigitou);
            saida.writeUTF(clientedigitou);
            saida.flush();


        } catch (IOException e) {
            System.out.println("IOException " + e);
        }

    }
}
