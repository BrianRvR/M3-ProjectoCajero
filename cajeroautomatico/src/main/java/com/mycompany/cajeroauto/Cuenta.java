/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumne
 */
public class Cuenta {
    
    private String idCuenta;
    private String tipo;
    private double saldoActual;
    private List<Movimiento> movimientos;

    public Cuenta(String idCuenta, String tipo, double saldoActual) {
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.saldoActual = saldoActual;
        this.movimientos = new ArrayList<>();
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getTipoCuenta() {
        return tipo;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void depositar(double monto) {
        saldoActual += monto;
        Movimiento movimiento = new Movimiento("ingreso", monto, "Depósito en cuenta");
        movimientos.add(movimiento);
    }

    public void retirar(double monto) {
        if (monto <= saldoActual) {
            saldoActual -= monto;
            Movimiento movimiento = new Movimiento("retiro", monto, "Retiro de cuenta");
            movimientos.add(movimiento);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    public void transferir(Cuenta cuentaDestino, double monto) {
        if (this.tipo.equals("corriente") && cuentaDestino.getTipoCuenta().equals("corriente")) {
            if (monto <= saldoActual) {
                saldoActual -= monto;
                cuentaDestino.depositar(monto);
                Movimiento movimiento = new Movimiento("transferencia", monto, "Transferencia a cuenta " + cuentaDestino.getIdCuenta());
                movimientos.add(movimiento);
                cuentaDestino.agregarMovimiento(movimiento);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente");
            }
        } else {
            throw new IllegalArgumentException("Tipo de cuenta no válido para transferencia");
        }
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public double calcularIntereses(double tasaInteres) {
        if (tipo.equals("ahorro")) {
            double intereses = saldoActual * tasaInteres;
            saldoActual += intereses;
            Movimiento movimiento = new Movimiento("intereses", intereses, "Cálculo de intereses");
            movimientos.add(movimiento);
            return intereses;
        } else {
            throw new IllegalArgumentException("Tipo de cuenta no válido para calcular intereses");
        }
    }
    
  
}


