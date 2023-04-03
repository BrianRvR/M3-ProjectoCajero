/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico;

import java.util.List;

/**
 *
 * @author windows 10
 */
public class Cuenta {
    private String cliente;
    private Double saldo;
    private List<Movimiento> movimientos;

    
    public Cuenta(String ncliente, Double nSaldo, List<Movimiento> nuevoMovimiento ){
    
        this.cliente=ncliente;
        this.saldo=nSaldo;
        this.movimientos=nuevoMovimiento;
    
    }

    public String getCliente() {
        return cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    
    
}