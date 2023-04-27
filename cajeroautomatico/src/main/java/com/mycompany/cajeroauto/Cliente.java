/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.ArrayList;
import java.util.List;

/**
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

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public Cuenta buscarCuenta(String idCuenta) {
    for (Cuenta cuenta : cuentas) {
        if (cuenta.getIdCuenta().equals(idCuenta)) {
            return cuenta;
        }
    }
    return null;
}


    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }
    
    public double obtenerSaldoTotal() {
    double saldoTotal = 0;
    for (Cuenta cuenta : cuentas) {
        saldoTotal += cuenta.getSaldoActual();
    }
    return saldoTotal;
}


    public boolean autenticar(String nombreUsuario, String password) {
        return this.nombreUsuario.equals(nombreUsuario) && this.password.equals(password);
    }
}

