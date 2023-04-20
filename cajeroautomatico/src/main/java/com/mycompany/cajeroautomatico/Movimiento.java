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
    //private List<Movimiento> listaMovimientos; ARRAY AVANZADA
    
    public Movimiento(String tipo, Date fecha, Double cantDinero){
        this.tipo=tipo;
        this.fecha=fecha;
        this.cantDinero=cantDinero;
    }
    //recuperar o devolver valor
    public String getTipo(){
        return this.tipo;
    }
    public Date getFecha(){
        return this.fecha;
    }
    public Double getCantDinero(){
        return this.cantDinero;
    }
    
    public void setTipo(String tipom){ //modificar
        this.tipo=tipom;
    }
    public void setFecha(Date fecham){ //modificar
        this.fecha=fecham;
    }
    public void setCantDinero(Double CantDinerom){ //modificar
        this.cantDinero=CantDinerom;
    }
    
}
