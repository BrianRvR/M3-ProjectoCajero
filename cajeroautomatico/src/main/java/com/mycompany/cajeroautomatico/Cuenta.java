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
    private String tipoCuenta;

    
    public Cuenta(String ncliente, Double nSaldo, List<Movimiento> nuevoMovimiento, String nTipo ){
    
        this.cliente=ncliente;
        this.saldo=nSaldo;
        this.movimientos=nuevoMovimiento;
        this.tipoCuenta=nTipo;
    
    }
    
    public void hacerMovimiento(Movimiento movimiento) {
        if(tipoCuenta == "corriente"){
            if (this.saldo < movimiento.getCantDinero()) {
                System.out.println("Oye que no puedes");
            } else {
                this.movimientos.add(movimiento);
                this.saldo = this.saldo - movimiento.getCantDinero();

            }
        } else{
            
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
       
        if(this.tipoCuenta == "corriente"){
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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    
    
    
    
}