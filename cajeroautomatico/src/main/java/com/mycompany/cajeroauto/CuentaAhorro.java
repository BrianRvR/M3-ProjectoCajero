/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

/**
 *
 * @author alumne
 */
public class CuentaAhorro extends Cuenta {
    private double tasaInteres;

    public CuentaAhorro(int idCuenta, double saldoActual, double tasaInteres) {
        super(idCuenta, "ahorro", saldoActual);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public String getTipoCuenta() {
        return "ahorro";
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
}

