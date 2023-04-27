/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

/**
 *
 * @author alumne
 */
public class CuentaCorriente extends Cuenta {
    private double limiteDescubierto;
    
    public CuentaCorriente(String idCuenta, double saldoActual, double limiteDescubierto) {
        super(idCuenta, "corriente", saldoActual);
        this.limiteDescubierto = limiteDescubierto;
    }
    
    public double getLimiteDescubierto() {
        return limiteDescubierto;
    }
    
    public void setLimiteDescubierto(double limiteDescubierto) {
        this.limiteDescubierto = limiteDescubierto;
    }
}

