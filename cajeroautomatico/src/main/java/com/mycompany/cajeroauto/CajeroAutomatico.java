/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.List;

/**
 *
 * @author alumne
 */
public class CajeroAutomatico {
     private Banco banco;
    private Cliente cliente;
    private Cuenta cuenta;

    public CajeroAutomatico(Banco banco, Cliente cliente, Cuenta cuenta) {
        this.banco = banco;
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    public void verSaldo() {
        System.out.println("Saldo actual: " + cuenta.getSaldoActual());
    }

    public void verMovimientos() {
        List<Movimiento> movimientos = cuenta.getMovimientos();
        for (Movimiento movimiento : movimientos) {
            System.out.println("Fecha: " + movimiento.getFechaHora() + " / Tipo: " + movimiento.getTipoMovimiento() + " / Monto: " + movimiento.getMonto() + " / Descripción: " + movimiento.getDescripcion());
        }
    }

    public void ingresar(double monto) {
        cuenta.depositar(monto);
        System.out.println("Depósito realizado. Nuevo saldo: " + cuenta.getSaldoActual());
    }

    public void retirar(double monto) {
        try {
            cuenta.retirar(monto);
            System.out.println("Retiro realizado. Nuevo saldo: " + cuenta.getSaldoActual());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public void transferir(String idCuentaDestino, double monto) {
        try {
            Cuenta cuentaDestino = banco.buscarCuenta(idCuentaDestino);
            cuenta.transferir(cuentaDestino, monto);
            System.out.println("Transferencia realizada. Nuevo saldo: " + cuenta.getSaldoActual());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }*/

}

