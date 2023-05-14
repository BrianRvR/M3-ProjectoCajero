/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    
    

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES"));
        return "[" + sdf.format(fechaHora) + "] " + tipoMovimiento + " - " + descripcion + ": " + monto;
    }

    public String toCSVLine() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tipoMovimiento + ";" + sdf.format(fechaHora) + ";" + monto + ";" + descripcion;
    }

}