/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico;

/**
 *
 * @author windows 10
 */
public class Cliente {
    
    private String usuario;
    private String contrasena;
    private Boolean bloqueo;
    private int loginIntentos = 3;
    
    public Cliente(){
        
    }
    
    public Cliente(String usuario, String contrasena, Boolean bloqueo){
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.bloqueo=bloqueo;
    }
    
    public String getUsuario() {
        
        return this.usuario;
    }
    private String getContrasena() {
        
        return this.contrasena;
    }
    private Boolean getBloqueo() {
        
        return this.bloqueo;
    }
    
    private int getLoginIntentos(){
        
        return this.loginIntentos;
    }
    
    public void setUsuario(String nuevoUsuario){
        this.usuario=nuevoUsuario;
    }
    private void setContrasena(String nuevaContrasena){
        this.usuario=nuevaContrasena;
    }
    private void setBloqueo(Boolean nuevoBloqueo){
        this.bloqueo=nuevoBloqueo;
    }
    private void setLoginIntentos(int nuevosIntentos){
        this.loginIntentos=nuevosIntentos;
    }
    
    
}


