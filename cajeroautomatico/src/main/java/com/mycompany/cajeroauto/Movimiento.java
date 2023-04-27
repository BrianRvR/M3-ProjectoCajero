/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.Date;

/**
 *
 * @author alumne
 */

public class Movimiento {
    
    private String tipoMovimiento;
    private Date fechaHora;
    private double monto;
    private String descripcion;

    public Movimiento(String tipoMovimiento, double monto, String descripcion) {
        this.tipoMovimiento = tipoMovimiento;
        this.fechaHora = new Date();
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }
  
}