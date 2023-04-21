/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico;

import java.util.Date;
//import java.util.List;

/**
 *
 * @author windows 10
 */
public class Movimiento {
    private String tipo;
    private Date fecha;
    private Double cantDinero;
    private Cuenta cuenta;
    
    public Movimiento(String tipo, Date fecha, Double cantDinero, Cuenta cuenta){
        this.tipo = tipo;
        this.fecha = fecha;
        this.cantDinero = cantDinero;
        this.cuenta = cuenta;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public Double getCantDinero(){
        return this.cantDinero;
    }
    
    public Cuenta getCuenta(){
        return this.cuenta;
    }
    
    public void setTipo(String tipom){
        this.tipo = tipom;
    }
    
    public void setFecha(Date fecham){
        this.fecha = fecham;
    }
    
    public void setCantDinero(Double CantDinerom){
        this.cantDinero = CantDinerom;
    }
    
    public void setCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }
}

