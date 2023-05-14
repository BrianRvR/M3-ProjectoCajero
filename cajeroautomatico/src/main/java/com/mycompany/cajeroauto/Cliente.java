/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**setContrasena
 *
 * @author alumne
 */
public class Cliente {
    private String nombreUsuario;
    private String password;
    private List<Cuenta> cuentas;


    public Cliente(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.cuentas = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }
    
    public void setContrasena(String nuevaContrasena) {
    password = nuevaContrasena;
    System.out.println("Contraseña actualizada: " + password);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }


    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
 
    public double obtenerSaldoTotalCuentas() {
    double saldoTotal = 0;
    for (Cuenta cuenta : cuentas) {
        saldoTotal += cuenta.getSaldoActual();
    }
    return saldoTotal;
}
    
   public Cuenta buscarCuentaPorId(int idCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getIdCuenta() == idCuenta) {
                return cuenta;
            }
        }
        return null;
    }
    
    public Cuenta buscarCuentaPorTipo(String tipo) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTipoCuenta().equalsIgnoreCase(tipo)) {
                return cuenta;
            }
        }
        return null;
    }
    

     public void agregarMovimientoACuenta(Movimiento movimiento, Cuenta cuenta) {
        for (Cuenta c : cuentas) {
            if (c.getIdCuenta() == cuenta.getIdCuenta()) {
                c.agregarMovimiento(movimiento);
                break;
            }
        }
    }
     
     

}

