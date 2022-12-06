/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsemilia;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author milif
 */
public class AgenciaBancaria {
    //Variáveis da agencia, deixando a lista de contas dentro de agencia
    public ArrayList<String> contas = new ArrayList<>();
    String descricao;
    String numeroAgencia = "0226";

    //Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

}
