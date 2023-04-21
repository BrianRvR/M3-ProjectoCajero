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
    private Cliente cliente;
    private Double saldo;
    private List<Movimiento> movimientos;
    private String tipoCuenta;

    public Cuenta(Cliente cliente, Double saldo, List<Movimiento> nuevoMovimiento, String tipoCuenta) {
        this.cliente = cliente;
        this.saldo = saldo;
        this.movimientos = nuevoMovimiento;
        this.tipoCuenta = tipoCuenta;
    }

    public void hacerMovimiento(Movimiento movimiento) {
        if(tipoCuenta.equals("corriente")) {
            if (this.saldo < movimiento.getCantDinero()) {
                System.out.println("Oye que no puedes");
            } else {
                this.movimientos.add(movimiento);
                this.saldo = this.saldo - movimiento.getCantDinero();
            }
        } else {
            
        }
    }

    public void movimientoIngresar(Movimiento movimientoI) {
        if (movimientoI.getCantDinero() <=0) {
            System.out.println("Oye que no puedes");
        } else {
            this.movimientos.add(movimientoI);
            this.saldo = this.saldo + movimientoI.getCantDinero();
        }
    }

    public void movimientoRetirar(Movimiento movimientoR) {
        if (this.saldo < movimientoR.getCantDinero() || movimientoR.getCantDinero() <=0) {
            System.out.println("Oye que no puedes retirar");
        } else {
            this.movimientos.add(movimientoR);
            this.saldo = this.saldo - movimientoR.getCantDinero();
        }
    }

    public void movimientoTransferencia(Movimiento movimientoT, Cuenta cuentaB) {
        if(this.tipoCuenta.equals("corriente")){
            if (this.saldo < movimientoT.getCantDinero() || movimientoT.getCantDinero() <=0) {
                System.out.println("Oye que no puedes hacer la transferencia");
            } else {
                this.movimientos.add(movimientoT);
                this.saldo = this.saldo - movimientoT.getCantDinero(); //aqui le resto el dinero de la cuenta A
                cuentaB.movimientoIngresar(movimientoT); //aqui le tranfiero el dinero B
            }
        } else{
            System.out.println("Cuenta ahorro no puedes");
        }
    }

    

    public Cliente getCliente() {
        return cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    

    

    
    
}