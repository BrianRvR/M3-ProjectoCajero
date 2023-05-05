/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumne
 */
public class Cuenta {
    
    private int idCuenta;
    private String tipo;
    private double saldoActual;
    private List<Movimiento> movimientos;

    public Cuenta(int idCuenta, String tipo, double saldoActual) {
    this.idCuenta = idCuenta;
    this.tipo = tipo;
    this.saldoActual = saldoActual;
    this.movimientos = new ArrayList<>();
}

    public int getIdCuenta() {
        return idCuenta;
    }

    public String getTipoCuenta() {
        return tipo;
    }

        
    public double getSaldoActual() {
        return saldoActual;
    }
    
    
    public void setSaldoActual(double saldo) {
        this.saldoActual = saldo;
        }

        public void actualizarSaldo() {
    saldoActual = 0.0;
    for (Movimiento movimiento : movimientos) {
        saldoActual += movimiento.getMonto();
    }
    System.out.println("Saldo actualizado en cuenta: " + saldoActual);

    System.out.println("Actualizando saldo en archivo CSV...");
    try {
        Banco.actualizarInfoCuentaCSV(this, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
        System.out.println("Saldo actualizado en archivo CSV.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void retirar(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("Monto a retirar debe ser mayor que cero");
    }
    if (monto > saldoActual) {
        throw new IllegalArgumentException("Saldo insuficiente");
    }
    saldoActual -= monto;
    Movimiento movimiento = new Movimiento("retiro", monto, "Retiro de cuenta");
    movimientos.add(movimiento);
    setSaldoActual(saldoActual); // actualizar el saldo de la cuenta
    System.out.println("Retiro de " + monto + " realizado correctamente.");

    System.out.println("Actualizando saldo en archivo CSV...");
    try {
        Banco.actualizarInfoCuentaCSV(this, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
        System.out.println("Saldo actualizado en archivo CSV.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void ingresar(double monto) {
        saldoActual += monto;
        Movimiento movimiento = new Movimiento("ingreso", monto, "Ingreso a cuenta");
        movimientos.add(movimiento);
        setSaldoActual(saldoActual); // actualizar el saldo de la cuenta
        System.out.println("Ingreso de " + monto + " realizado correctamente.");

        System.out.println("Actualizando saldo en archivo CSV...");
        try {
            Banco.actualizarInfoCuentaCSV(this, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
            System.out.println("Saldo actualizado en archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
    
    public List<Movimiento> obtenerMovimientosCuenta() {
        return this.movimientos;
    }
    
    

    public void depositar(double monto) {
        saldoActual += monto;
        Movimiento movimiento = new Movimiento("ingreso", monto, "Depósito en cuenta");
        movimientos.add(movimiento);
    }

   
    
    public boolean verificarSaldoSuficiente(double monto) {
        return monto <= saldoActual;
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


