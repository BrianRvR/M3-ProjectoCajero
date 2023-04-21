/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author windows 10
 */
public class Cliente {
    
    private String nombreUsuario;
    private String contrasena;
    private List<Cuenta> cuentas;
    
    public Cliente(String usuario, String contrasena /*Boolean bloqueo*/){
        this.nombreUsuario=usuario;
        this.contrasena=contrasena;
        this.cuentas = new ArrayList<>();
    }
    
    public String getNombreUsuario() {
        
        return nombreUsuario;
    }
    public String getContrasena() {
        
        return contrasena;
    }
    
    private void setContrasena(String nuevaContrasena){
        this.nombreUsuario=nuevaContrasena;
    }
    
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
    
    public List<Cuenta> getCuentas() {
    return cuentas;
    }

    
    
    
}


