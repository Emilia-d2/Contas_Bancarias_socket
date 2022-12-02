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
public class AgenciaBancaria extends Thread {

    public static void main(String args[]) {
        
        //Inicializa o ServerSocket da agencia
        ServerSocket agencia = null;
        try {
            agencia = new ServerSocket(40001);
            System.out.println("---------------------------------------");
            System.out.println("BEM VINDO AO BANCO PAGUE BEM!          |");
            System.out.println("Aguardadndo gerente...                 |");
            System.out.println("---------------------------------------");
            while (true) {
                
                //Quando o gerente se conecta, a thread inicializa da agencia bacaria.
                Socket conexao = agencia.accept();
                System.out.println("Gerente se conectou: " + conexao.getInetAddress().getHostAddress());
                Thread t = new AgenciaBancaria(conexao);
                t.start();

            }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
    
    //Socket de conexao da agencia bancaria
    private Socket conexao;

    public AgenciaBancaria(Socket agencia) {
        conexao = agencia;
    }
    
    //Inicializa o processo de comunicação das threads e do socket com o gerente
    //Onde pega a entrada de dados e mostra no servidor a saida do que o gerente digitou
    public void run() {

        try {
            while(true){
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            String gerenteDigitou = entrada.readUTF();
            System.out.println("Gerente Digitou: " + gerenteDigitou);
            saida.writeUTF(gerenteDigitou);
            }

        } catch (IOException e) {
            System.out.println("IOException " + e);
        }

    }
}
