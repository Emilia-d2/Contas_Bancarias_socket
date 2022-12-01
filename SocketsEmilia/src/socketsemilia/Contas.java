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
class Contas {
    public String conta;
    private List contas = new ArrayList();
    private double saldo;
    
    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public List getContas() {
        return contas;
    }

    public void setContas(List contas) {
        this.contas = contas;
    }
    
    
}
